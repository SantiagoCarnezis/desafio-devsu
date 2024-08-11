package com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String numeroCuenta) {
        super(Mensajes.SALDO_INSUFICIENTE + numeroCuenta);
    }
}