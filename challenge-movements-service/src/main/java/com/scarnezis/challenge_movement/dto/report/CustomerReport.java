package com.scarnezis.challenge_movement.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CustomerReport {

    private String clienteId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<AccountReport> reportesPorCuentas = new LinkedList<>();

    public void add(AccountReport accountReport) {
        reportesPorCuentas.add(accountReport);
    }
}
