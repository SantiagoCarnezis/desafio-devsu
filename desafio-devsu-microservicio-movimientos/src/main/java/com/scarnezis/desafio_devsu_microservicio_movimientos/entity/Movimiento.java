package com.scarnezis.desafio_devsu_microservicio_movimientos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull(message = "La fecha no puede ser nula.")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime fecha;
    @NotNull(message = "El cliente no puede ser nulo.")
    private String clienteId;
//    @NotNull(message = "El nombre del cliente no puede ser nulo.")
//    private String nombreCliente;
    @NotNull(message = "El tipo no puede ser nulo.")
    private TipoCuenta tipo;
    @NotNull(message = "El valor no puede ser nulo.")
    private Double valor;
    @NotNull(message = "El saldo no puede ser nulo.")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "numero_cuenta")
    private Cuenta cuenta;
}
