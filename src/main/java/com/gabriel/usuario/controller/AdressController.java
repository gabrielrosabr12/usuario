package com.gabriel.usuario.controller;

import com.gabriel.usuario.infraestruture.dto.AdressDTO;
import com.gabriel.usuario.infraestruture.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adress")
@RequiredArgsConstructor
public class AdressController {

    private final AdressService adressService;

    @PutMapping("/update")
    public ResponseEntity<AdressDTO> updateAdress(@AuthenticationPrincipal Jwt jwt, @RequestBody AdressDTO adressDTO){
        AdressDTO adressDTOSalvo = this.adressService.updateAdress(jwt, adressDTO);
        return ResponseEntity.ok().body(adressDTOSalvo);
    }
}
