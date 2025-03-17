package com.scarnezis.challenge_movement.service;

import com.scarnezis.challenge_movement.dto.account.CreateAccount;
import com.scarnezis.challenge_movement.entity.Account;
import com.scarnezis.challenge_movement.entity.EstadoCuenta;
import com.scarnezis.challenge_movement.excepcion.AccountNotFoundException;
import com.scarnezis.challenge_movement.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AccountService {

    private final AccountRepository accountRepository;
    private final String closeAccountTopic = "close-account-topic";

    public List<Account> getAllAccounts(String clienteId) {

        return accountRepository.findAllByOwnerId(clienteId);
    }

    public Account getAccountByNumber(String accountNumber) {

        return accountRepository.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    public Account createAccount(CreateAccount cuentaDto) {

        Account account = new Account();
        account.setOwnerId(cuentaDto.getOwnerId());
        account.setBalance(1000.0);
        account.setType(cuentaDto.getType());
        account.setState(EstadoCuenta.OPEN);

        return accountRepository.save(account);
    }

    public void deleteAccount(String accountNumber) {

        accountRepository.deleteById(accountNumber);
    }

    @KafkaListener(topics = closeAccountTopic)
    public void closeAccount(String accountNumber) {

        Account account = accountRepository.findById(accountNumber).
                orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.setState(EstadoCuenta.CLOSED);
        accountRepository.save(account);
    }
}

