package com.scarnezis.challenge_movement.excepcion;

public class AccountClosedException extends RuntimeException {

    public AccountClosedException(String mensaje, String numeroCuenta) {
        super(mensaje + numeroCuenta);
    }
}