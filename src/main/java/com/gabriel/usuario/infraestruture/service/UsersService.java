package com.gabriel.usuario.infraestruture.service;

import com.gabriel.usuario.infraestruture.converter.Converter;
import com.gabriel.usuario.infraestruture.converter.UserMapperUpdater;
import com.gabriel.usuario.infraestruture.dto.UserDTOCreate;
import com.gabriel.usuario.infraestruture.dto.UserDTOUpdate;
import com.gabriel.usuario.infraestruture.entity.Adress;
import com.gabriel.usuario.infraestruture.entity.UserRole;
import com.gabriel.usuario.infraestruture.entity.Users;
import com.gabriel.usuario.infraestruture.exceptions.InconsistentData;
import com.gabriel.usuario.infraestruture.exceptions.UserAlreadyExistException;
import com.gabriel.usuario.infraestruture.exceptions.UserNotFound;
import com.gabriel.usuario.infraestruture.repository.UserRepository;
import com.gabriel.usuario.infraestruture.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final Converter converter;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdressService adressService;
    private final UserMapperUpdater userMapperUpdater;

    public Boolean validateUser(Jwt jwt){
        Optional<Users> users = this.userRepository.findByUsername(jwt.getSubject());

        if (users.isEmpty()) throw new UserNotFound();

        return Boolean.TRUE;
    }

    @Transactional
    public void createUser(UserDTOCreate userDTOCreate){

        if (userRepository.existsByUsername(userDTOCreate.username())){
            throw new UserAlreadyExistException();
        }

        Users novoUsuario = this.converter.toUsers(userDTOCreate);

        UserRole role = this.userRoleRepository.findByDescription("ROLE_USER").orElseThrow(
                () -> new RuntimeException("Role User not exists"));
        novoUsuario.setRole(role);

        String hash = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(hash);

        if (userDTOCreate.adressDTO() != null){
            Adress adress = this.adressService.createAdress(userDTOCreate.adressDTO());
            novoUsuario.setAdress(adress);
        }

        this.userRepository.save(novoUsuario);

    }
    @Transactional
    public UserDTOCreate updateUser(Long id, Jwt jwt, UserDTOUpdate userDTOUpdate){
        Users users = this.userRepository.findById(id)
                .orElseThrow(UserNotFound::new);

        

        // 1. Validação do usuário logado via JWT (deve ser o mesmo do cadastro)
        if (!users.getUsername().equals(jwt.getSubject())) {
            throw new InconsistentData(
                    "O usuário logado não é igual ao usuário que está sendo editado.",
                    "Inconsistent logged user for user update data"
            );
        }

        // 2. Validação condicional: Só compara o username se ele tiver sido enviado no DTO
        if (userDTOUpdate.username() != null && !users.getUsername().equals(userDTOUpdate.username())) {
            throw new InconsistentData(
                    "O username enviado no payload difere do nome do usuário cadastrado.",
                    "Inconsistent user update data"
            );
        }

        // 3. Aplica as alterações no objeto persistido através do MapStruct
        userMapperUpdater.updateEntityFromDto(userDTOUpdate, users);

        // 4. Se a senha tiver sido alterada no PATCH, encrita novamente
        if (userDTOUpdate.password() != null && !userDTOUpdate.password().isBlank()) {
            users.setPassword(passwordEncoder.encode(userDTOUpdate.password()));
        }

        // 5. Persiste as alterações e retorna o DTO atualizado
        Users userSaved = userRepository.save(users);

        return this.converter.toUserDTO(userSaved);
    }
}