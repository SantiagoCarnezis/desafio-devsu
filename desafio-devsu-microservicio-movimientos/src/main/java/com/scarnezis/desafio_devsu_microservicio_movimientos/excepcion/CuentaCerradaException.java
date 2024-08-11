package com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;

public class CuentaCerradaException extends RuntimeException {

    public CuentaCerradaException(String mensaje, String numeroCuenta) {
        super(mensaje + numeroCuenta);
    }
}