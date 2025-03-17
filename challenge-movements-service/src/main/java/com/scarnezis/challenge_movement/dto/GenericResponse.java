package com.scarnezis.challenge_movement.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class GenericResponse {

    private int code;
    private String message;
    private Object data;
    private List<String> errors;

    public static GenericResponse successfulResponse(String mensaje, Object body) {

        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(mensaje);
        response.setData(body);

        return response;
    }

    public static GenericResponse successfulResponse(String mensaje) {

        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(mensaje);
        response.setData(null);

        return response;
    }

    public static GenericResponse failedResponse(String mensaje) {
        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrors(List.of(mensaje));
        response.setMessage("Solicitud fallida");
        return response;
    }

    public static GenericResponse failedResponse(List<String> mensajes) {
        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrors(mensajes);
        response.setMessage("Solicitud fallida");
        return response;
    }
}
