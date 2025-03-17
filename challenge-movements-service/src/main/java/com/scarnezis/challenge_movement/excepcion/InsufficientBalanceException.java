package com.scarnezis.challenge_movement.excepcion;

import com.scarnezis.challenge_movement.dto.Messages;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String numeroCuenta) {
        super(Messages.INSUFFICIENT_BALANCE + numeroCuenta);
    }
}