package com.scarnezis.challenge_movement.service;

import com.scarnezis.challenge_movement.dto.Messages;
import com.scarnezis.challenge_movement.dto.movement.CreateMovementDto;
import com.scarnezis.challenge_movement.entity.Account;
import com.scarnezis.challenge_movement.entity.EstadoCuenta;
import com.scarnezis.challenge_movement.entity.Movement;
import com.scarnezis.challenge_movement.excepcion.AccountClosedException;
import com.scarnezis.challenge_movement.excepcion.AccountNotFoundException;
import com.scarnezis.challenge_movement.excepcion.MovementNotFoundException;
import com.scarnezis.challenge_movement.repository.AccountRepository;
import com.scarnezis.challenge_movement.repository.MovementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    public List<Movement> getAllMovements(String accountNumber) {

        return movementRepository.findAllByAccount_AccountNumber(accountNumber);
    }

    public Movement getMovementById(String id) {
        return movementRepository.findById(id).orElseThrow(() -> new MovementNotFoundException(id));
    }

    @Transactional
    public Movement pay(CreateMovementDto movementDto) {

        Account accountDebtor = accountRepository.findById(movementDto.getAccountNumberDebtor())
                .orElseThrow(() -> new AccountNotFoundException(movementDto.getAccountNumberDebtor()));

        if(accountDebtor.getState().equals(EstadoCuenta.CLOSED))
            throw new AccountClosedException(Messages.CLOSED_ACCOUNT_MAKE_PAYMENT, movementDto.getAccountNumberDebtor());

        accountDebtor.subtract(movementDto.getValue());

        Account accountCreditor = accountRepository.findById(movementDto.getAccountNumberCreditor())
                .orElseThrow(() -> new AccountNotFoundException(movementDto.getAccountNumberCreditor()));

        if(accountCreditor.getState().equals(EstadoCuenta.CLOSED))
            throw new AccountClosedException(Messages.CLOSED_ACCOUNT_RECEIVE_PAYMENT, movementDto.getAccountNumberDebtor());

        accountCreditor.add(movementDto.getValue());

        Movement movementDebtor = new Movement();
        movementDebtor.setCustomerId(accountCreditor.getOwnerId());
        movementDebtor.setDate(LocalDateTime.now());
        movementDebtor.setType(accountCreditor.getType());
        movementDebtor.setAmount(-movementDto.getValue());
        movementDebtor.setBalance(accountDebtor.getBalance());
        movementDebtor.setAccount(accountDebtor);

        Movement movementCreditor = new Movement();
        movementCreditor.setCustomerId(accountDebtor.getOwnerId());
        movementCreditor.setDate(LocalDateTime.now());
        movementCreditor.setType(accountDebtor.getType());
        movementCreditor.setAmount(movementDto.getValue());
        movementCreditor.setBalance(accountCreditor.getBalance());
        movementCreditor.setAccount(accountCreditor);

        movementRepository.save(movementDebtor);
        movementRepository.save(movementCreditor);

        return movementDebtor;
    }
}
