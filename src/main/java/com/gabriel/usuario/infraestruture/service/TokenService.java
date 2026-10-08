package com.gabriel.usuario.infraestruture.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    // Injeta o jwtEncoder que criamos na Config
    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication){
        //Recebe a hora atual
        Instant instant = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder() //Constroí um objeto JwtClaimsSet
                .issuer("api-usuarios") // Apelido do emissor do token
                .issuedAt(instant) // Hora atual da emissão
                .expiresAt(instant.plus(2, ChronoUnit.HOURS)) //Hora que expira
                .subject(authentication.getName()) // Usuario do token
                .claim("scope",scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue(); // Retorna o token já gerado

    }

}
