package com.scarnezis.challenge_movement.excepcion;

import com.scarnezis.challenge_movement.dto.Messages;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String numeroCuenta) {
        super(Messages.ACCOUNT_NOT_FOUND + numeroCuenta);
    }
}