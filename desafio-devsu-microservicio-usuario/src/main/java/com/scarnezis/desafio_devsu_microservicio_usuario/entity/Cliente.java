package com.scarnezis.desafio_devsu_microservicio_usuario.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("C")
@Getter
@Setter
public class Cliente extends Persona {

    private String password;
    private boolean estado;
}
