package com.scarnezis.desafio_devsu_microservicio_movimientos.service;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento.ObtenerMovimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte.ReportePorCliente;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte.ReportePorCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Movimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.CuentaNoEncontradaException;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.CuentaRepository;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ReporteService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public ReportePorCliente getReportesPorCliente(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        List<String> numerosDeCuenta = cuentaRepository.findNumeroCuentaByDuenioId(clienteId);

        ReportePorCliente reportePorCliente = new ReportePorCliente();
        reportePorCliente.setClienteId(clienteId);
        reportePorCliente.setFechaInicio(fechaInicio);
        reportePorCliente.setFechaFin(fechaFin);

        for (String numeroCuenta:numerosDeCuenta) {

            ReportePorCuenta reportePorCuenta = this.getReportesPorCuenta(numeroCuenta, fechaInicio, fechaFin);
            reportePorCliente.add(reportePorCuenta);
        }

        return reportePorCliente;
    }

    public ReportePorCuenta getReportesPorCuenta(String numeroCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));

        List<ObtenerMovimiento> movimientos = movimientoRepository.getReportesPorCuenta(numeroCuenta, fechaInicio, fechaFin);

        ReportePorCuenta reportePorCuenta = new ReportePorCuenta();
        reportePorCuenta.setNumeroCuenta(numeroCuenta);

        if(!movimientos.isEmpty()) {

            reportePorCuenta.setSaldoInicial(movimientos.get(0).getSaldo() - movimientos.get(0).getValor());
            reportePorCuenta.setSaldoFinal(movimientos.get(movimientos.size()-1).getSaldo());
            reportePorCuenta.setMovimientos(movimientos);
        }
        else {

            reportePorCuenta.setMovimientos(new ArrayList<>());

            Movimiento ultimoMovimientoAntesFechaInicio =
                    movimientoRepository.findFirstByCuentaNumeroCuentaAndFechaBeforeOrderByFechaDesc(numeroCuenta, fechaInicio);

            if(ultimoMovimientoAntesFechaInicio != null)
                reportePorCuenta.setSaldoInicial(ultimoMovimientoAntesFechaInicio.getSaldo());
            else
                reportePorCuenta.setSaldoInicial(cuenta.getSaldo());

            reportePorCuenta.setSaldoFinal(reportePorCuenta.getSaldoInicial());
        }

        return reportePorCuenta;
    }
}
