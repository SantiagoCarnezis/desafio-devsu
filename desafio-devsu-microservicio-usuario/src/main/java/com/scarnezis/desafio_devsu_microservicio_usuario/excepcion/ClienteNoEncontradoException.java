package com.scarnezis.desafio_devsu_microservicio_usuario.excepcion;

import com.scarnezis.desafio_devsu_microservicio_usuario.dto.Mensajes;

public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(String id) {
        super(Mensajes.CLIENTE_NO_ENCONTRADO + id);
    }
}