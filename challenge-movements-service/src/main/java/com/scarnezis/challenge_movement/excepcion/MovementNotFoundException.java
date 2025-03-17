package com.scarnezis.challenge_movement.excepcion;

public class MovementNotFoundException extends RuntimeException {

    public MovementNotFoundException(String id) {
        super("Movement with id " + id + " not found");
    }
}