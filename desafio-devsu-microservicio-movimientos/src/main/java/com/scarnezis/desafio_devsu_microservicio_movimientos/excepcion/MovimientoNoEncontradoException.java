package com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion;

public class MovimientoNoEncontradoException extends RuntimeException {

    public MovimientoNoEncontradoException(String id) {
        super("No se encontro el movimiento con id " + id);
    }
}