package com.gabriel.usuario.infraestruture.repository;

import com.gabriel.usuario.infraestruture.entity.Adress;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdressRepository extends JpaRepository<Adress,Long> {

    public Optional<Adress> findByAdress(String adress);
}
