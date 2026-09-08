package com.gabriel.usuario.infraestruture.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_users_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description",length = 100, unique = true, nullable = false)
    private String description;

    public UserRole(String description) {
        this.description = description;
    }
}
