package com.gabriel.usuario.infraestruture.repository;

import com.gabriel.usuario.infraestruture.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
