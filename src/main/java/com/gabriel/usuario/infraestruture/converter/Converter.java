package com.gabriel.usuario.infraestruture.converter;

import com.gabriel.usuario.infraestruture.dto.AdressDTO;
import com.gabriel.usuario.infraestruture.dto.UserDTOCreate;
import com.gabriel.usuario.infraestruture.entity.Adress;
import com.gabriel.usuario.infraestruture.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public UserDTOCreate toUserDTO(Users users){
        if (users == null) {
            return null;
        }

        return UserDTOCreate.builder()
                .username(users.getUsername())
                .name(users.getName())
                .adressDTO(users.getAdress() != null ? toAdressDTO(users.getAdress()):null)
                .build();
    }

    public Users toUsers(UserDTOCreate userDTOCreate){
        return Users.builder()
                .username(userDTOCreate.username())
                .name(userDTOCreate.name())
                .password(userDTOCreate.password())
                .adress(userDTOCreate.adressDTO() != null ? this.toAdress(userDTOCreate.adressDTO()):null)
                .build();
    }

    public AdressDTO toAdressDTO(Adress adress){
        if (adress == null) {
            return null;
        }

        return AdressDTO.builder()
                .adress(adress.getAdress())
                .zipCode(adress.getZipCode())
                .propertyNumber(adress.getPropertyNumber())
                .supplement(adress.getSupplement())
                .district(adress.getDistrict())
                .build();
    }

    public Adress toAdress(AdressDTO adressDTO){
        if (adressDTO == null){
            return null;
        }


        return Adress.builder()
                .adress(adressDTO.adress())
                .district(adressDTO.district())
                .zipCode(adressDTO.zipCode())
                .supplement(adressDTO.supplement())
                .propertyNumber(adressDTO.propertyNumber())
                .build();
    }


}
