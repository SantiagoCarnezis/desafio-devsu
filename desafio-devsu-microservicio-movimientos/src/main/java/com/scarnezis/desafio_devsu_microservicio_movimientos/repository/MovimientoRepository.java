package com.scarnezis.desafio_devsu_microservicio_movimientos.repository;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento.ObtenerMovimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, String> {

    List<Movimiento> findAllByCuentaNumeroCuenta(String numeroCuenta);

    @Query("SELECT new com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento.ObtenerMovimiento(m.id, m.fecha, m.clienteId, m.tipo, m.valor, m.saldo) " +
            "FROM Movimiento m " +
            "WHERE m.cuenta.numeroCuenta = :numeroCuenta AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin " +
            "ORDER BY m.fecha")
    List<ObtenerMovimiento> getReportesPorCuenta(@Param("numeroCuenta") String numeroCuenta,
                                                 @Param("fechaInicio") LocalDateTime fechaInicio,
                                                 @Param("fechaFin") LocalDateTime fechaFin);
    Movimiento findFirstByCuentaNumeroCuentaAndFechaBeforeOrderByFechaDesc(String numeroCuenta, LocalDateTime fecha);
}

