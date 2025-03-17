package com.scarnezis.challenge_user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GenericResponse {

    private int code;
    private String message;
    private Object data;
    private List<String> errors = new ArrayList<>();

    public static GenericResponse successfulResponse(String mensaje, Object body) {

        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(mensaje);
        response.setData(body);

        return response;
    }

    public static GenericResponse getFailedResponse(String mensaje) {
        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrors(List.of(mensaje));
        response.setMessage("Solicitud fallida");
        return response;
    }

    public static GenericResponse getFailedResponse(List<String> mensajes) {
        GenericResponse response = new GenericResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrors(mensajes);
        response.setMessage("Solicitud fallida");
        return response;
    }

    public void addError(String error) {
        errors.add(error);
    }
}
