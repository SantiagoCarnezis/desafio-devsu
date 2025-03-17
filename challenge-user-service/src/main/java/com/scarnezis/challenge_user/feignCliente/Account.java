package com.scarnezis.challenge_user.feignCliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountNumber;
    private String ownerId;
    private TipoCuenta type;
    private Double balance;
    private EstadoCuenta state;
}
