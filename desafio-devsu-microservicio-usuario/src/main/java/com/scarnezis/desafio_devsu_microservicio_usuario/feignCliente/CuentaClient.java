package com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente;

import com.scarnezis.desafio_devsu_microservicio_usuario.dto.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.ConnectException;

@FeignClient("cuenta")
public interface CuentaClient {

    @GetMapping("/api/cuentas")
    ResponseEntity<GenericResponse> getCuentas(@RequestParam("clienteId") String personId) throws ConnectException;
}