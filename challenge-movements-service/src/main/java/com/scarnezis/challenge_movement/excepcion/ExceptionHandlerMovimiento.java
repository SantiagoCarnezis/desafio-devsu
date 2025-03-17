package com.scarnezis.challenge_movement.excepcion;

import com.scarnezis.challenge_movement.dto.GenericResponse;
import com.scarnezis.challenge_movement.dto.Messages;
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

    @ExceptionHandler(value = {AccountNotFoundException.class, MovementNotFoundException.class})
    protected ResponseEntity<GenericResponse> resourceNotFound(Exception ex) {

        GenericResponse response = GenericResponse.failedResponse(ex.getMessage());
        response.setCode(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = AccountClosedException.class)
    protected ResponseEntity<GenericResponse> accountClosed(Exception ex) {

        GenericResponse response = GenericResponse.failedResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    protected ResponseEntity<GenericResponse> insufficientBalance(Exception ex) {

        GenericResponse response = GenericResponse.failedResponse(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value= {HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    protected ResponseEntity<GenericResponse> invalidRequest(Exception ex) {

        GenericResponse response = GenericResponse.failedResponse(Messages.INVALID_BODY);

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
    protected ResponseEntity<GenericResponse> SQLerrors(ConstraintViolationException ex) {

        List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        GenericResponse response = GenericResponse.failedResponse(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
