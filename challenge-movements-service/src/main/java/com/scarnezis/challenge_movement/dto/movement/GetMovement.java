package com.scarnezis.challenge_movement.dto.movement;

import com.scarnezis.challenge_movement.entity.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetMovement {

    private String id;
    private LocalDateTime date;
    private String customerId;
    private TipoCuenta type;
    private Double value;
    private Double balance;
}
