package com.scarnezis.challenge_movement.dto.account;

import com.scarnezis.challenge_movement.entity.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccount {

    private String ownerId;
    private TipoCuenta type;
}
