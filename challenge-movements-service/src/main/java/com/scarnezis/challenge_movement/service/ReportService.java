package com.scarnezis.challenge_movement.service;

import com.scarnezis.challenge_movement.dto.movement.GetMovement;
import com.scarnezis.challenge_movement.dto.report.CustomerReport;
import com.scarnezis.challenge_movement.dto.report.AccountReport;
import com.scarnezis.challenge_movement.entity.Account;
import com.scarnezis.challenge_movement.entity.Movement;
import com.scarnezis.challenge_movement.excepcion.AccountNotFoundException;
import com.scarnezis.challenge_movement.repository.AccountRepository;
import com.scarnezis.challenge_movement.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ReportService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    public CustomerReport getReportsByCustomer(String customerId, LocalDateTime initialDate, LocalDateTime endDate) {

        List<String> accountNumbers = accountRepository.findAccountNumberByOwnerId(customerId);

        CustomerReport customerReport = new CustomerReport();
        customerReport.setClienteId(customerId);
        customerReport.setFechaInicio(initialDate);
        customerReport.setFechaFin(endDate);

        for (String accountNumber:accountNumbers) {

            AccountReport accountReport = this.getReportsByAccount(accountNumber, initialDate, endDate);
            customerReport.add(accountReport);
        }

        return customerReport;
    }

    public AccountReport getReportsByAccount(String accountNumber, LocalDateTime initialDate, LocalDateTime endDate) {

        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));

        List<GetMovement> movements = movementRepository.getReportsByAccount(accountNumber, initialDate, endDate);

        AccountReport accountReport = new AccountReport();
        accountReport.setAccountNumber(accountNumber);

        if(!movements.isEmpty()) {

            accountReport.setInitialBalance(movements.get(0).getBalance() - movements.get(0).getValue());
            accountReport.setFinalBalance(movements.get(movements.size()-1).getBalance());
            accountReport.setMovements(movements);
        }
        else {

            accountReport.setMovements(new ArrayList<>());

            Movement lastMovementBeforeInitialDate =
                    movementRepository.findFirstByAccount_AccountNumberAndDateBeforeOrderByDateDesc(accountNumber, initialDate);

            if(lastMovementBeforeInitialDate != null)
                accountReport.setInitialBalance(lastMovementBeforeInitialDate.getBalance());
            else
                accountReport.setInitialBalance(account.getBalance());

            accountReport.setFinalBalance(accountReport.getInitialBalance());
        }

        return accountReport;
    }
}
