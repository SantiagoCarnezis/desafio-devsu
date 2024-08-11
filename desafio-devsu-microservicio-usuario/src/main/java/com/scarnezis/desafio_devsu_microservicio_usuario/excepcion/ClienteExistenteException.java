package com.scarnezis.desafio_devsu_microservicio_usuario.excepcion;

public class ClienteExistenteException extends RuntimeException {

    public ClienteExistenteException(String identificacion) {
        super("El cliente de identificacion " + identificacion + " ya existe");
    }
}