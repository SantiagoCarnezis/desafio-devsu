package com.scarnezis.desafio_devsu_microservicio_movimientos.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class GenericResponse {

    private int codigo;
    private String mensaje;
    private Object data;
    private List<String> errors;

    public static GenericResponse responseExistosa(String mensaje, Object body) {

        GenericResponse response = new GenericResponse();
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(mensaje);
        response.setData(body);

        return response;
    }

    public static GenericResponse responseExistosa(String mensaje) {

        GenericResponse response = new GenericResponse();
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(mensaje);
        response.setData(null);

        return response;
    }

    public static GenericResponse responseFallida(String mensaje) {
        GenericResponse response = new GenericResponse();
        response.setCodigo(HttpStatus.BAD_REQUEST.value());
        response.setErrors(List.of(mensaje));
        response.setMensaje("Solicitud fallida");
        return response;
    }

    public static GenericResponse responseFallida(List<String> mensajes) {
        GenericResponse response = new GenericResponse();
        response.setCodigo(HttpStatus.BAD_REQUEST.value());
        response.setErrors(mensajes);
        response.setMensaje("Solicitud fallida");
        return response;
    }
}
