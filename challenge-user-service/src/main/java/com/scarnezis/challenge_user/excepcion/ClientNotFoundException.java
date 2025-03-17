package com.scarnezis.challenge_user.excepcion;

import com.scarnezis.challenge_user.dto.Messages;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String id) {
        super(Messages.CLIENT_NOT_FOUND + id);
    }
}