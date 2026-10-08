package com.gabriel.usuario.infraestruture.repository;

import com.gabriel.usuario.infraestruture.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    public Optional<UserRole> findByDescription(String description);
}
