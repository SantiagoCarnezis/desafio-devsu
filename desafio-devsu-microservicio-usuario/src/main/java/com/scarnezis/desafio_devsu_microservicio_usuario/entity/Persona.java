package com.scarnezis.desafio_devsu_microservicio_usuario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="cliente", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("P")
@Getter
@Setter
public class Persona {

    @Id
    private String identificacion;
    private String nombre;
    private boolean genero;
    private int edad;
    private String direccion;
    private String telefono;
}
