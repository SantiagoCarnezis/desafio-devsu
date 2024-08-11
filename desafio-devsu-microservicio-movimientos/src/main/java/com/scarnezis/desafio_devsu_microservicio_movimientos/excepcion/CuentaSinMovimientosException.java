package com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion;

public class CuentaSinMovimientosException extends RuntimeException {

    public CuentaSinMovimientosException(String numeroCuenta) {
        super("No se encontraron movimientos para la cuenta de numero " + numeroCuenta);
    }
}