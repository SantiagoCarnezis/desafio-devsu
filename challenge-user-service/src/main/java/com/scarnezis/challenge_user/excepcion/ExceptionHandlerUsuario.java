package com.scarnezis.challenge_user.excepcion;

import com.scarnezis.challenge_user.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class ExceptionHandlerUsuario {

    @ExceptionHandler(value= {HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    protected ResponseEntity<GenericResponse> solicitudInvalida(Exception ex) {

        GenericResponse response = GenericResponse.getFailedResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = ExistingClientException.class)
    protected ResponseEntity<GenericResponse> clienteExistente(ExistingClientException ex) {

        GenericResponse response = GenericResponse.getFailedResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = ClientNotFoundException.class)
    protected ResponseEntity<GenericResponse> clienteNoEncontrado(ClientNotFoundException ex) {

        GenericResponse response = GenericResponse.getFailedResponse(ex.getMessage());
        response.setCode(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {ConnectException.class})
    protected ResponseEntity<GenericResponse> conexionRehusada(ConnectException ex) {

        GenericResponse response = GenericResponse.getFailedResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
