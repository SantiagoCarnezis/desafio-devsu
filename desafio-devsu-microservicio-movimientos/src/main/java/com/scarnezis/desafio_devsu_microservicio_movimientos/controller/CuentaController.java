package com.scarnezis.desafio_devsu_microservicio_movimientos.controller;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.GenericResponse;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.cuenta.CrearCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping
    public GenericResponse getAllCuentas(@RequestParam String clienteId) {

        List<Cuenta> cuentas = cuentaService.getAllCuentas(clienteId);
        GenericResponse response;

        if (cuentas.isEmpty())
            response = GenericResponse.responseExistosa(Mensajes.CLIENTE_SIN_CUENTAS);
        else
            response = GenericResponse.responseExistosa(Mensajes.CUENTA_ENCONTRADA, cuentas);

        return response;
    }

    @GetMapping("/{numeroCuenta}")
    public GenericResponse getCuentaByNumero(@PathVariable String numeroCuenta) {
        Cuenta cuenta = cuentaService.getCuentaByNumero(numeroCuenta);
        return GenericResponse.responseExistosa(Mensajes.CUENTA_ENCONTRADA, cuenta);
    }

    @PostMapping
    public GenericResponse createCuenta(@RequestBody CrearCuenta cuentaDto) {
        Cuenta cuenta =  cuentaService.createCuenta(cuentaDto);
        return GenericResponse.responseExistosa(Mensajes.CUENTA_CREADA, cuenta);
    }

    @DeleteMapping("/{numeroCuenta}")
    public GenericResponse deleteCuenta(@PathVariable String numeroCuenta) {
        cuentaService.deleteCuenta(numeroCuenta);
        return GenericResponse.responseExistosa(Mensajes.CUENTA_ELIMINADA);
    }
}

