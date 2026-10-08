package com.gabriel.usuario.infraestruture.service;


import com.gabriel.usuario.dto.RequestLoginDto;
import com.gabriel.usuario.infraestruture.entity.Users;
import com.gabriel.usuario.infraestruture.exceptions.UserNotFound;
import com.gabriel.usuario.infraestruture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String validateLogin(RequestLoginDto requestLoginDto){
        Users users = this.userRepository.findByUsername(requestLoginDto.usuario()).orElseThrow(UserNotFound::new);

        boolean match = this.passwordEncoder.matches(requestLoginDto.password(),users.getPassword());

        if (!match){
            throw new BadCredentialsException("Usuário ou senha inválidos!");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(requestLoginDto.usuario(),null, users.getAuthorities());
        String token = this.tokenService.generateToken(auth);
        return token;
    }

}
