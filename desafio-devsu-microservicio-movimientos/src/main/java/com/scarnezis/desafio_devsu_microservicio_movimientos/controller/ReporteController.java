package com.scarnezis.desafio_devsu_microservicio_movimientos.controller;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.GenericResponse;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte.ReportePorCliente;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte.ReportePorCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/cliente")
    public GenericResponse getReportesPorCliente(@RequestParam String clienteId,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime fechaInicio,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime fechaFin) {

        ReportePorCliente reportePorCliente = reporteService.getReportesPorCliente(clienteId, fechaInicio, fechaFin);
        return GenericResponse.responseExistosa(Mensajes.REPORTES_ENCONTRADOS, reportePorCliente);
    }

    @GetMapping("/cuenta")
    public GenericResponse getReportesPorCuenta(@RequestParam String numeroCuenta,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime fechaInicio,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime fechaFin) {

        ReportePorCuenta reportePorCuenta = reporteService.getReportesPorCuenta(numeroCuenta, fechaInicio, fechaFin);
        return GenericResponse.responseExistosa(Mensajes.REPORTES_ENCONTRADOS, reportePorCuenta);
    }
}
