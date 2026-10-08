package com.gabriel.usuario.infraestruture.repository;


import com.gabriel.usuario.infraestruture.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    public Optional<Users> findByUsername(String username);

    public Boolean existsByUsername(String username);
}
