package com.gabriel.usuario.infraestruture.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTOUpdate(
        @NotBlank(message = "Name may not be empty")
        String username,
        String name,
        String password,
        AdressDTO adressDTO) {

}
