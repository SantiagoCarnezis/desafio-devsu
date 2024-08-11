package com.scarnezis.desafio_devsu_microservicio_usuario.excepcion;

import com.scarnezis.desafio_devsu_microservicio_usuario.dto.GenericResponse;

public class DevSuFeignClientException extends RuntimeException {

    private final GenericResponse response;

    public DevSuFeignClientException(GenericResponse response) {

        this.response = response;
    }

    public GenericResponse getResponse() {
        return response;
    }
}