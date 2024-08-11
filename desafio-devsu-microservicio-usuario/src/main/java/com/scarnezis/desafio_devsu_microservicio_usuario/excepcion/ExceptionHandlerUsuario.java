package com.scarnezis.desafio_devsu_microservicio_usuario.excepcion;

import com.scarnezis.desafio_devsu_microservicio_usuario.dto.GenericResponse;
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

        GenericResponse response = GenericResponse.getRespuestaFallida(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = ClienteExistenteException.class)
    protected ResponseEntity<GenericResponse> clienteExistente(ClienteExistenteException ex) {

        GenericResponse response = GenericResponse.getRespuestaFallida(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = ClienteNoEncontradoException.class)
    protected ResponseEntity<GenericResponse> clienteNoEncontrado(ClienteNoEncontradoException ex) {

        GenericResponse response = GenericResponse.getRespuestaFallida(ex.getMessage());
        response.setCodigo(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {ConnectException.class})
    protected ResponseEntity<GenericResponse> conexionRehusada(ConnectException ex) {

        GenericResponse response = GenericResponse.getRespuestaFallida(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
