package com.scarnezis.challenge_movement.dto.report;

import com.scarnezis.challenge_movement.dto.movement.GetMovement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountReport {

    private String accountNumber;
    private Double initialBalance;
    private Double finalBalance;
    private List<GetMovement> movements;
}
