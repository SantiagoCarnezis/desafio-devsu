package com.scarnezis.desafio_devsu_microservicio_usuario.dto;

import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class ObtenerCliente {

    private String identificacion;
    private String nombre;
    private boolean genero;
    private int edad;
    private String direccion;
    private String telefono;
    private boolean estado;
    private List<Cuenta> cuentas;

    public ObtenerCliente(String identificacion, String nombre, boolean genero, int edad, String direccion, String telefono, boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
        this.cuentas = new ArrayList<>();
    }
}
