package com.scarnezis.challenge_movement.dto.account;

import com.scarnezis.challenge_movement.entity.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccount {

    private TipoCuenta type;
    private Double initialBalance;
    private boolean state;
}
