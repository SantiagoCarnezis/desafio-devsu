package com.scarnezis.desafio_devsu_microservicio_movimientos.controller;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.GenericResponse;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento.CrearMovimientoDto;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Movimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping
    public GenericResponse getAllMovimientos(@RequestParam String numeroCuenta) {

        List<Movimiento> movimientos = movimientoService.getAllMovimientos(numeroCuenta);

        GenericResponse response;

        if (movimientos.isEmpty())
            response = GenericResponse.responseExistosa(Mensajes.CUENTA_SIN_MOVIMIENTOS);
        else
            response = GenericResponse.responseExistosa(Mensajes.MOVIMIENTOS_DE_LA_CUENTA, movimientos);

        return response;
    }

    @GetMapping("/{id}")
    public GenericResponse getMovimientoById(@PathVariable String id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id);
        return GenericResponse.responseExistosa(Mensajes.MOVIMIENTO_ENCONTRADO, movimiento);
    }

    @PostMapping
    public GenericResponse realizarPago(@RequestBody CrearMovimientoDto movimientoDto) {

        Movimiento movimiento = movimientoService.realizarPago(movimientoDto);
        return GenericResponse.responseExistosa(Mensajes.PAGO_REALIZADO, movimiento);
    }
}
