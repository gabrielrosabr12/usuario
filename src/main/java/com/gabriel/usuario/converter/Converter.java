package com.gabriel.usuario.converter;

import com.gabriel.usuario.dto.AdressDTO;
import com.gabriel.usuario.dto.UserDTO;
import com.gabriel.usuario.infraestruture.entity.Adress;
import com.gabriel.usuario.infraestruture.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public UserDTO toUserDTO(Users users){
        if (users == null) {
            return null;
        }

        return UserDTO.builder()
                .username(users.getUsername())
                .name(users.getName())
                .adressDTO(users.getAdress() != null ? toAdressDTO(users.getAdress()):null)
                .build();
    }

    public Users toUsers(UserDTO userDTO){
        return Users.builder()
                .username(userDTO.username())
                .name(userDTO.name())
                .password(userDTO.password())
                .adress(userDTO.adressDTO() != null ? this.toAdress(userDTO.adressDTO()):null)
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
