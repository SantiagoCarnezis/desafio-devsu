package com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento.ObtenerMovimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Movimiento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReportePorCuenta {

    private String numeroCuenta;
    private Double saldoInicial;
    private Double saldoFinal;
    private List<ObtenerMovimiento> movimientos;
}
