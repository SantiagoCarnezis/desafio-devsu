package com.scarnezis.challenge_movement.entity;

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
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull(message = "Date cannot be null.")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime date;
    @NotNull(message = "Customer cannot be null.")
    private String customerId;
//    @NotNull(message = "El nombre dCustomer cannot be null.")
//    private String nombreCliente;
    @NotNull(message = "Type cannot be null.")
    private TipoCuenta type;
    @NotNull(message = "Amount cannot be null")
    private Double amount;
    @NotNull(message = "Balance cannot be null.")
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;
}
