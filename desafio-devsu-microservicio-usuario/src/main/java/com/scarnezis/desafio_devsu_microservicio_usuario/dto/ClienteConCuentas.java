package com.scarnezis.desafio_devsu_microservicio_usuario.dto;

import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.Cuenta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteConCuentas {

    private String identificacion;
    private String nombre;
    private boolean genero;
    private int edad;
    private String direccion;
    private String telefono;
    private boolean estado;
    private List<Cuenta> cuentas;
}
