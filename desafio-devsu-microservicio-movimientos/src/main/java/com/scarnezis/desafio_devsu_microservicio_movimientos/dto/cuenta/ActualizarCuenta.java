package com.scarnezis.desafio_devsu_microservicio_movimientos.dto.cuenta;

import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.TipoCuenta;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarCuenta {

    private TipoCuenta tipo;
    private Double saldoInicial;
    private boolean estado;
}
