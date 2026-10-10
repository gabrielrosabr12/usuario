package com.gabriel.usuario.controller;

import com.gabriel.usuario.infraestruture.dto.RequestLoginDto;
import com.gabriel.usuario.infraestruture.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RequestLoginDto request){
        String token = this.authService.validateLogin(request);
        return ResponseEntity.ok().body(token);
    }
}
