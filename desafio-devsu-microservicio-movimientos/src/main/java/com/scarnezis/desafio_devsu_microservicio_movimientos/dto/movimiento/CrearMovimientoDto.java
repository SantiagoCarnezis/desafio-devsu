package com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearMovimientoDto {

    private String numeroCuentaAcreedor;
    private String numeroCuentaDeudor;
    private Double valor;
}
