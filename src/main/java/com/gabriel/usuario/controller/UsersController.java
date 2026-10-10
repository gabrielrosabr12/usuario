package com.gabriel.usuario.controller;

import com.gabriel.usuario.infraestruture.dto.UserDTOCreate;
import com.gabriel.usuario.infraestruture.dto.UserDTOUpdate;
import com.gabriel.usuario.infraestruture.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService userService;

    public UsersController(UsersService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTOCreate> createUser(@RequestBody @Valid UserDTOCreate userDTOCreate){
        this.userService.createUser(userDTOCreate);
        return ResponseEntity.ok().body(userDTOCreate);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserDTOCreate> updateUser(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt, @RequestBody UserDTOUpdate userDTOUpdate){
        UserDTOCreate user = this.userService.updateUser(id,jwt,userDTOUpdate);


        return ResponseEntity.ok().body(user);
    }



}
