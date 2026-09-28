package com.gabriel.usuario.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
   @NotBlank(message = "username cannot be empty")
   @Size(min=5,max=50,message = "Username must be between 5 and 50 characters")
   String username,
   @NotBlank(message= "name cannot be empty")
   @Size(min=2,max=50,message = "Your accont name must be between 2 and 50 characters")
   String name,
   @NotBlank(message= "Your password cannot be empty")
   @Size(min=5,max=30,message="Your password must be between 5 and 30 characters")
   String password
   ){}
