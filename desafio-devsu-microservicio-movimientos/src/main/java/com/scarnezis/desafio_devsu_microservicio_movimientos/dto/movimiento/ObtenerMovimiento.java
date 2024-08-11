package com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento;

import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.TipoCuenta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ObtenerMovimiento {

    private String id;
    private LocalDateTime fecha;
    private String clienteId;
    private TipoCuenta tipo;
    private Double valor;
    private Double saldo;
}
