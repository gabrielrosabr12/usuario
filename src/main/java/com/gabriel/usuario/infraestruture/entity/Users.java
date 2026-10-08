package com.gabriel.usuario.infraestruture.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Users implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", length = 50,unique = true,nullable = false)
    private String username;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="password", length = 255, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name="role_id")
    private UserRole role;

    @ManyToOne(targetEntity = Adress.class)
    @JoinColumn(name="id_adress",columnDefinition = "BIGINT")
    private Adress adress;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null || this.role.getDescription() == null) {
            return List.of();
        }

        return switch (this.role.getDescription()) {
            case "ROLE_ADMIN" -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_DEFAULT")
            );
            case "ROLE_DEFAULT" -> List.of(
                    new SimpleGrantedAuthority("ROLE_DEFAULT"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            case "ROLE_USER" -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );

            default -> List.of();
        };
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
