package com.scarnezis.challenge_movement.entity;


import com.scarnezis.challenge_movement.excepcion.InsufficientBalanceException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountNumber;
    @NotNull(message = "Owner cannot be null.")
    private String ownerId;
    @NotNull(message = "Account type cannot be null.")
    private TipoCuenta type;
    @NotNull(message = "Balance cannot be null.")
    private Double balance;
    @NotNull(message = "State cannot be null.")
    private EstadoCuenta state;

    public void subtract(Double value) {

        if (balance < value)
            throw new InsufficientBalanceException(accountNumber);

        balance = balance - value;
    }

    public void add(Double value) {

        balance = balance + value;
    }
}

