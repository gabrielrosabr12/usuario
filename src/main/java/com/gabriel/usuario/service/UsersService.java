package com.gabriel.usuario.service;

import com.gabriel.usuario.converter.Converter;
import com.gabriel.usuario.dto.UserDTO;
import com.gabriel.usuario.infraestruture.entity.Adress;
import com.gabriel.usuario.infraestruture.entity.UserRole;
import com.gabriel.usuario.infraestruture.entity.Users;
import com.gabriel.usuario.infraestruture.exceptions.UserAlreadyExistException;
import com.gabriel.usuario.infraestruture.repository.UserRepository;
import com.gabriel.usuario.infraestruture.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Transactional
    public void createUser(UserDTO userDTO){

        if (userRepository.existsByUsername(userDTO.username())){
            throw new UserAlreadyExistException();
        }

        Users novoUsuario = this.converter.toUsers(userDTO);

        UserRole role = this.userRoleRepository.findByDescription("ROLE_USER").orElseThrow(
                () -> new RuntimeException("Role User not exists"));
        novoUsuario.setRole(role);

        String hash = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(hash);

        if (userDTO.adressDTO() != null){
            Adress adress = this.adressService.createAdress(userDTO.adressDTO());
            novoUsuario.setAdress(adress);
        }

        this.userRepository.save(novoUsuario);


    }


}
