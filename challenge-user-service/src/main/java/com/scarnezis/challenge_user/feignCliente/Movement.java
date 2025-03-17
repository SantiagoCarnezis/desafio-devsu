package com.scarnezis.challenge_user.feignCliente;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Movement {

    private String id;
    private LocalDateTime date;
    private String clientId;
    private TipoCuenta type;
    private Double value;
    private Double balance;
}
