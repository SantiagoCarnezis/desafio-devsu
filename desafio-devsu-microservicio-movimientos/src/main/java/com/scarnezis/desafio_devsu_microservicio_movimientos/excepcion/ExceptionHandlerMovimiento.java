package com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.GenericResponse;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerMovimiento {

    @ExceptionHandler(value = {CuentaNoEncontradaException.class, MovimientoNoEncontradoException.class})
    protected ResponseEntity<GenericResponse> recursoNoEncontrado(Exception ex) {

        GenericResponse response = GenericResponse.responseFallida(ex.getMessage());
        response.setCodigo(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = CuentaCerradaException.class)
    protected ResponseEntity<GenericResponse> cuentaCerrada(Exception ex) {

        GenericResponse response = GenericResponse.responseFallida(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = SaldoInsuficienteException.class)
    protected ResponseEntity<GenericResponse> saldoInsuficiente(Exception ex) {

        GenericResponse response = GenericResponse.responseFallida(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value= {HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    protected ResponseEntity<GenericResponse> solicitudInvalida(Exception ex) {

        GenericResponse response = GenericResponse.responseFallida(Mensajes.BODY_NO_VALIDO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ExceptionHandler(value ={DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class})
//    protected ResponseEntity<GenericResponse> registroDuplicado(SQLIntegrityConstraintViolationException ex) {
//
//        GenericResponse response = GenericResponse.responseFallida(Mensajes.REGISTRO_DUPLICADO);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }

    @ExceptionHandler(value= ConstraintViolationException.class)
    protected ResponseEntity<GenericResponse> erroresSQL(ConstraintViolationException ex) {

        List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        GenericResponse response = GenericResponse.responseFallida(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
