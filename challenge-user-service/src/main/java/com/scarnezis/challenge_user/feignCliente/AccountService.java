package com.scarnezis.challenge_user.feignCliente;

import com.scarnezis.challenge_user.dto.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.ConnectException;

@FeignClient("cuenta")
public interface AccountService {

    @GetMapping("/api/cuentas")
    ResponseEntity<GenericResponse> getAccounts(@RequestParam("clienteId") String personId) throws ConnectException;
}