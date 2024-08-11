package com.scarnezis.desafio_devsu_microservicio_movimientos.repository;

import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    List<Cuenta> findAllByDuenioId(String clienteId);

    @Query("SELECT c.numeroCuenta FROM Cuenta c WHERE c.duenioId = :duenioId")
    List<String> findNumeroCuentaByDuenioId(@Param("duenioId") String duenioId);
}

