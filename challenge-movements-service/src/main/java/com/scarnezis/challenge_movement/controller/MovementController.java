package com.scarnezis.challenge_movement.controller;

import com.scarnezis.challenge_movement.dto.GenericResponse;
import com.scarnezis.challenge_movement.dto.Messages;
import com.scarnezis.challenge_movement.dto.movement.CreateMovementDto;
import com.scarnezis.challenge_movement.entity.Movement;
import com.scarnezis.challenge_movement.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MovementController {

    private final MovementService movementService;

    @GetMapping
    public GenericResponse getAllMovements(@RequestParam String accountNumber) {

        List<Movement> movements = movementService.getAllMovements(accountNumber);

        GenericResponse response;

        if (movements.isEmpty())
            response = GenericResponse.successfulResponse(Messages.ACCOUNT_WITHOUT_TRANSACTIONS);
        else
            response = GenericResponse.successfulResponse(Messages.ACCOUNT_TRANSACTIONS, movements);

        return response;
    }

    @GetMapping("/{id}")
    public GenericResponse getMovementById(@PathVariable String id) {
        Movement movement = movementService.getMovementById(id);
        return GenericResponse.successfulResponse(Messages.TRANSACTION_FOUND, movement);
    }

    @PostMapping
    public GenericResponse pay(@RequestBody CreateMovementDto movementDto) {

        Movement movement = movementService.pay(movementDto);
        return GenericResponse.successfulResponse(Messages.PAYMENT_COMPLETED, movement);
    }
}
