package com.scarnezis.desafio_devsu_microservicio_movimientos.entity;


import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.SaldoInsuficienteException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numeroCuenta;
    @NotNull(message = "El dueño no puede ser nulo.")
    private String duenioId;
    @NotNull(message = "El tipo de cuenta no puede ser nulo.")
    private TipoCuenta tipo;
    @NotNull(message = "El saldo no puede ser nulo.")
    private Double saldo;
    @NotNull(message = "El estado no puede ser nulo.")
    private EstadoCuenta estado;

    public void restarSaldo(Double valor) {

        if (saldo < valor)
            throw new SaldoInsuficienteException(numeroCuenta);

        saldo = saldo - valor;
    }

    public void sumarSaldo(Double valor) {

        saldo = saldo + valor;
    }
}

