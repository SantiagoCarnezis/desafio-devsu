package com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    private String numeroCuenta;
    private String duenioId;
    private TipoCuenta tipo;
    private Double saldo;
    private EstadoCuenta estado;
}
