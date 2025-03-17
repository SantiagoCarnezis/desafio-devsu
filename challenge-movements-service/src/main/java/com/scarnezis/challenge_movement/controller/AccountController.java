package com.scarnezis.challenge_movement.controller;

import com.scarnezis.challenge_movement.dto.GenericResponse;
import com.scarnezis.challenge_movement.dto.Messages;
import com.scarnezis.challenge_movement.dto.account.CreateAccount;
import com.scarnezis.challenge_movement.entity.Account;
import com.scarnezis.challenge_movement.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public GenericResponse getAllAccounts(@RequestParam String customerId) {

        List<Account> accounts = accountService.getAllAccounts(customerId);
        GenericResponse response;

        if (accounts.isEmpty())
            response = GenericResponse.successfulResponse(Messages.CLIENT_WITHOUT_ACCOUNTS);
        else
            response = GenericResponse.successfulResponse(Messages.ACCOUNT_FOUND, accounts);

        return response;
    }

    @GetMapping("/{accountNumber}")
    public GenericResponse getAccountByNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        return GenericResponse.successfulResponse(Messages.ACCOUNT_FOUND, account);
    }

    @PostMapping
    public GenericResponse createAccount(@RequestBody CreateAccount accountDto) {
        Account account =  accountService.createAccount(accountDto);
        return GenericResponse.successfulResponse(Messages.ACCOUNT_CREATED, account);
    }

    @DeleteMapping("/{accountNumber}")
    public GenericResponse deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return GenericResponse.successfulResponse(Messages.ACCOUNT_DELETED);
    }
}

