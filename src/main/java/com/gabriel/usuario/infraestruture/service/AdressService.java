package com.gabriel.usuario.infraestruture.service;

import com.gabriel.usuario.infraestruture.converter.Converter;
import com.gabriel.usuario.infraestruture.dto.AdressDTO;
import com.gabriel.usuario.infraestruture.entity.Adress;
import com.gabriel.usuario.infraestruture.repository.AdressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdressService {
    private final AdressRepository adressRepository;
    private final Converter converter;
    private final JwtDecoder jwtDecoder;
    private final UsersService usersService;


    public Adress createAdress(AdressDTO adressDTO){
        return adressRepository.save(this.converter.toAdress(adressDTO));
    };

    public AdressDTO updateAdress(Jwt jwt,AdressDTO adressDTO){
        this.usersService.validateUser(jwt).booleanValue();
        Adress adress = this.converter.toAdress(adressDTO);
        Adress adressSalvo = this.adressRepository.save(adress);
        return this.converter.toAdressDTO(adressSalvo);
    }
}
