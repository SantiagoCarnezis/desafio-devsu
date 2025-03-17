package com.scarnezis.challenge_user.excepcion;

public class ExistingClientException extends RuntimeException {

    public ExistingClientException(String id) {
        super("Already existing client for " + id);
    }
}