package com.gabriel.usuario.service;

import com.gabriel.usuario.converter.Converter;
import com.gabriel.usuario.dto.AdressDTO;
import com.gabriel.usuario.infraestruture.entity.Adress;
import com.gabriel.usuario.infraestruture.repository.AdressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdressService {
    private final AdressRepository adressRepository;
    private final Converter converter;


    public Adress createAdress(AdressDTO adressDTO){
        return adressRepository.save(this.converter.toAdress(adressDTO));
    };

}
