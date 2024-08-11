package com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ReportePorCliente {

    private String clienteId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<ReportePorCuenta> reportesPorCuentas = new LinkedList<>();

    public void add(ReportePorCuenta reportePorCuenta) {
        reportesPorCuentas.add(reportePorCuenta);
    }
}
