package com.scarnezis.desafio_devsu_microservicio_movimientos;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.reporte.ReportePorCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.EstadoCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Movimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.TipoCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.CuentaNoEncontradaException;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.CuentaRepository;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.MovimientoRepository;
import com.scarnezis.desafio_devsu_microservicio_movimientos.service.ReporteService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;


@DataJpaTest
@Import({ReporteService.class})
@ActiveProfiles("test")
@Transactional
public class ReporteIntegrationTest {

    @Autowired
    private ReporteService reporteService;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    public void testReporteCuentaConMovimientosEnLasFechasIndicas() {

        String duenioId = "00112233";

        Double saldoInicial = 1000.0;
        Cuenta cuenta = getCuenta(duenioId, saldoInicial);

        cuenta = cuentaRepository.save(cuenta);

        Double valorMovimiento1 = -150.0;
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setFecha(LocalDateTime.now().minusHours(3));
        movimiento1.setClienteId(duenioId);
        movimiento1.setTipo(TipoCuenta.AHORRO);
        movimiento1.setValor(valorMovimiento1);
        movimiento1.setSaldo(850.0);
        movimiento1.setCuenta(cuenta);
        movimientoRepository.save(movimiento1);

        Double valorMovimiento2 = -50.0;
        Movimiento movimiento2 = new Movimiento();
        movimiento2.setFecha(LocalDateTime.now().minusHours(2));
        movimiento2.setClienteId(duenioId);
        movimiento2.setTipo(TipoCuenta.AHORRO);
        movimiento2.setValor(valorMovimiento2);
        movimiento2.setSaldo(800.0);
        movimiento2.setCuenta(cuenta);
        movimientoRepository.save(movimiento2);

        LocalDateTime fechaMenorConMovimientos = LocalDateTime.now().minusHours(4);
        LocalDateTime fechaMayorConMovimientos = LocalDateTime.now().minusHours(1);

        ReportePorCuenta reporte = reporteService.getReportesPorCuenta(
                cuenta.getNumeroCuenta(), fechaMenorConMovimientos, fechaMayorConMovimientos);

        Assertions.assertNotNull(reporte);
        Assertions.assertEquals(cuenta.getNumeroCuenta(), reporte.getNumeroCuenta());
        Assertions.assertEquals(2, reporte.getMovimientos().size());
        Assertions.assertEquals(saldoInicial, reporte.getSaldoInicial());
        Assertions.assertEquals(800.0, reporte.getSaldoFinal());
    }

    @Test
    public void testReporteCuentaSinMovimientosEnLasFechasIndicas() {

        String duenioId = "00112233";

        Double saldoInicial = 1000.0;
        Cuenta cuenta = getCuenta(duenioId, saldoInicial);

        cuenta = cuentaRepository.save(cuenta);

        Double valorMovimiento1 = -300.0;
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setFecha(LocalDateTime.now().minusHours(5));
        movimiento1.setClienteId(duenioId);
        movimiento1.setTipo(TipoCuenta.AHORRO);
        movimiento1.setValor(valorMovimiento1);
        movimiento1.setSaldo(700.0);
        movimiento1.setCuenta(cuenta);
        movimientoRepository.save(movimiento1);

        Double valorMovimiento2 = -50.0;
        Movimiento movimiento2 = new Movimiento();
        movimiento2.setFecha(LocalDateTime.now().minusHours(1));
        movimiento2.setClienteId(duenioId);
        movimiento2.setTipo(TipoCuenta.AHORRO);
        movimiento2.setValor(valorMovimiento2);
        movimiento2.setSaldo(650.0);
        movimiento2.setCuenta(cuenta);
        movimientoRepository.save(movimiento2);

        LocalDateTime fechaMenorSinMovimientos = LocalDateTime.now().minusHours(4);
        LocalDateTime fechaMayorSinMovimientos = LocalDateTime.now().minusHours(2);

        ReportePorCuenta reporte = reporteService.getReportesPorCuenta(
                cuenta.getNumeroCuenta(), fechaMenorSinMovimientos, fechaMayorSinMovimientos);

        Assertions.assertNotNull(reporte);
        Assertions.assertEquals(cuenta.getNumeroCuenta(), reporte.getNumeroCuenta());
        Assertions.assertTrue(reporte.getMovimientos().isEmpty());
        Assertions.assertEquals(700.0, reporte.getSaldoInicial());
        Assertions.assertEquals(700.0, reporte.getSaldoFinal());
    }

    @Test
    public void testReporteCuentaSinMovimientos() {

        String duenioId = "00112233";

        Double saldoInicial = 1000.0;
        Cuenta cuenta = getCuenta(duenioId, saldoInicial);

        cuenta = cuentaRepository.save(cuenta);

        LocalDateTime fechaInicio = LocalDateTime.now().minusHours(1);
        LocalDateTime fechaFin = LocalDateTime.now();

        ReportePorCuenta reporte = reporteService.getReportesPorCuenta(cuenta.getNumeroCuenta(), fechaInicio, fechaFin);

        Assertions.assertNotNull(reporte);
        Assertions.assertEquals(cuenta.getNumeroCuenta(), reporte.getNumeroCuenta());
        Assertions.assertTrue(reporte.getMovimientos().isEmpty());
        Assertions.assertEquals(1000.0, reporte.getSaldoInicial());
        Assertions.assertEquals(1000.0, reporte.getSaldoFinal());
    }

    @Test
    public void testReporteCuentaInexistente() {

        String numeroCuenta = "0000";
        LocalDateTime fechaInicio = LocalDateTime.now().minusHours(1);
        LocalDateTime fechaFin = LocalDateTime.now();

        Assertions.assertThrows(CuentaNoEncontradaException.class, () -> {
            reporteService.getReportesPorCuenta(numeroCuenta, fechaInicio, fechaFin);
        });
    }

    private static Cuenta getCuenta(String duenioId, Double saldoInicial) {
        Cuenta cuenta = new Cuenta();
        cuenta.setEstado(EstadoCuenta.ABIERTA);
        cuenta.setDuenioId(duenioId);
        cuenta.setSaldo(saldoInicial);
        cuenta.setTipo(TipoCuenta.AHORRO);
        return cuenta;
    }
}
