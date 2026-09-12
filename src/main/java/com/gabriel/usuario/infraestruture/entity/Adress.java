package com.gabriel.usuario.infraestruture.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tb_adress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="adress",nullable = false,length = 255)
    private String adress;

    @Column(name="zip_code",nullable = false,length = 15)
    private String zipCode;

    @Column(name="property_number", nullable = true)
    private Integer propertyNumber;

    @Column(name="supplement",length = 255,nullable = true)
    private String supplement;

    @Column(name="district", length = 100)
    private String district;

    @OneToMany(targetEntity = Users.class,mappedBy = "adress")
    private List<Users> users;

}
