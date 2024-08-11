package com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;

public class CuentaNoEncontradaException extends RuntimeException {

    public CuentaNoEncontradaException(String numeroCuenta) {
        super(Mensajes.CUENTA_NO_ENCONTRADA + numeroCuenta);
    }
}