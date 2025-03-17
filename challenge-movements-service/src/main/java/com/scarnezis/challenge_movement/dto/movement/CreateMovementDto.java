package com.scarnezis.challenge_movement.dto.movement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMovementDto {

    private String accountNumberCreditor;
    private String accountNumberDebtor;
    private Double value;
}
