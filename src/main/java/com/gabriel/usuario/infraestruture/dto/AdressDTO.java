package com.gabriel.usuario.infraestruture.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

@Builder
public record AdressDTO(
        @NotBlank(message = "adress cannot be empty")
        @Size(min=5,max=255, message="Adress must be between 5 and 255 characters")
        String adress,
        @NotBlank(message="zipCode cannot be empty")
        @Size(min=15,max=15, message="zipCode must to have 15 characters")
        String zipCode,
        Integer propertyNumber,
        @Size(min=1,max=255,message = "Has supplement must have between 1 and 255 characters")
        String supplement,
        @Size(max=100, message="Has district can be 100 characters")
        String district
) {
}
