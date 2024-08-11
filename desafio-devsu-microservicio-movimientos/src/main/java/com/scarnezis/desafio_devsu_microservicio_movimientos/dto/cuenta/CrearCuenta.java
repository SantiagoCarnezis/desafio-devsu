package com.scarnezis.desafio_devsu_microservicio_movimientos.dto.cuenta;

import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearCuenta {

    private String duenioId;
    private TipoCuenta tipo;
}
