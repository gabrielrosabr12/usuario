package com.gabriel.usuario.infraestruture.repository;


import com.gabriel.usuario.infraestruture.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
