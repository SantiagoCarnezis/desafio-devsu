package com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Movimiento {

    private String id;
    private LocalDateTime fecha;
    private String clienteId;
    private TipoCuenta tipo;
    private Double valor;
    private Double saldo;
}
