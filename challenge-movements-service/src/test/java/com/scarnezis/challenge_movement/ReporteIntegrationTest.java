package com.scarnezis.challenge_movement;

import com.scarnezis.challenge_movement.dto.report.AccountReport;
import com.scarnezis.challenge_movement.entity.Account;
import com.scarnezis.challenge_movement.entity.EstadoCuenta;
import com.scarnezis.challenge_movement.entity.Movement;
import com.scarnezis.challenge_movement.entity.TipoCuenta;
import com.scarnezis.challenge_movement.excepcion.AccountNotFoundException;
import com.scarnezis.challenge_movement.repository.AccountRepository;
import com.scarnezis.challenge_movement.repository.MovementRepository;
import com.scarnezis.challenge_movement.service.ReportService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;


@DataJpaTest
@Import({ReportService.class})
@ActiveProfiles("test")
@Transactional
public class ReporteIntegrationTest {

    @Autowired
    private ReportService reportService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MovementRepository movementRepository;

    @Test
    public void testReporteCuentaConMovimientosEnLasFechasIndicas() {

        String ownerId = "00112233";

        Double initialBalance = 1000.0;
        Account account = getAccount(ownerId, initialBalance);

        account = accountRepository.save(account);

        Double movementValue1 = -150.0;
        Movement movement1 = new Movement();
        movement1.setDate(LocalDateTime.now().minusHours(3));
        movement1.setCustomerId(ownerId);
        movement1.setType(TipoCuenta.SAVINGS_ACCOUNT);
        movement1.setAmount(movementValue1);
        movement1.setBalance(850.0);
        movement1.setAccount(account);
        movementRepository.save(movement1);

        Double movementValue2 = -50.0;
        Movement movement2 = new Movement();
        movement2.setDate(LocalDateTime.now().minusHours(2));
        movement2.setCustomerId(ownerId);
        movement2.setType(TipoCuenta.SAVINGS_ACCOUNT);
        movement2.setAmount(movementValue2);
        movement2.setBalance(800.0);
        movement2.setAccount(account);
        movementRepository.save(movement2);

        LocalDateTime initialDateWithMovements = LocalDateTime.now().minusHours(4);
        LocalDateTime endDateWithMovements = LocalDateTime.now().minusHours(1);

        AccountReport report = reportService.getReportsByAccount(
                account.getAccountNumber(), initialDateWithMovements, endDateWithMovements);

        Assertions.assertNotNull(report);
        Assertions.assertEquals(account.getAccountNumber(), report.getAccountNumber());
        Assertions.assertEquals(2, report.getMovements().size());
        Assertions.assertEquals(initialBalance, report.getInitialBalance());
        Assertions.assertEquals(800.0, report.getFinalBalance());
    }

    @Test
    public void testReporteCuentaSinMovimientosEnLasFechasIndicas() {

        String ownerId = "00112233";

        Double initialBalance = 1000.0;
        Account account = getAccount(ownerId, initialBalance);

        account = accountRepository.save(account);

        Double movementValue1 = -300.0;
        Movement movement1 = new Movement();
        movement1.setDate(LocalDateTime.now().minusHours(5));
        movement1.setCustomerId(ownerId);
        movement1.setType(TipoCuenta.SAVINGS_ACCOUNT);
        movement1.setAmount(movementValue1);
        movement1.setBalance(700.0);
        movement1.setAccount(account);
        movementRepository.save(movement1);

        Double movementValue2 = -50.0;
        Movement movement2 = new Movement();
        movement2.setDate(LocalDateTime.now().minusHours(1));
        movement2.setCustomerId(ownerId);
        movement2.setType(TipoCuenta.SAVINGS_ACCOUNT);
        movement2.setAmount(movementValue2);
        movement2.setBalance(650.0);
        movement2.setAccount(account);
        movementRepository.save(movement2);

        LocalDateTime initialDateWithoutMovements = LocalDateTime.now().minusHours(4);
        LocalDateTime endDateWithoutMovements = LocalDateTime.now().minusHours(2);

        AccountReport report = reportService.getReportsByAccount(
                account.getAccountNumber(), initialDateWithoutMovements, endDateWithoutMovements);

        Assertions.assertNotNull(report);
        Assertions.assertEquals(account.getAccountNumber(), report.getAccountNumber());
        Assertions.assertTrue(report.getMovements().isEmpty());
        Assertions.assertEquals(700.0, report.getInitialBalance());
        Assertions.assertEquals(700.0, report.getFinalBalance());
    }

    @Test
    public void testReporteCuentaSinMovimientos() {

        String ownerId = "00112233";

        Double initialBalance = 1000.0;
        Account account = getAccount(ownerId, initialBalance);

        account = accountRepository.save(account);

        LocalDateTime initialDate = LocalDateTime.now().minusHours(1);
        LocalDateTime endDate = LocalDateTime.now();

        AccountReport report = reportService.getReportsByAccount(account.getAccountNumber(), initialDate, endDate);

        Assertions.assertNotNull(report);
        Assertions.assertEquals(account.getAccountNumber(), report.getAccountNumber());
        Assertions.assertTrue(report.getMovements().isEmpty());
        Assertions.assertEquals(1000.0, report.getInitialBalance());
        Assertions.assertEquals(1000.0, report.getFinalBalance());
    }

    @Test
    public void testReporteCuentaInexistente() {

        String numeroCuenta = "0000";
        LocalDateTime initialDate = LocalDateTime.now().minusHours(1);
        LocalDateTime endDate = LocalDateTime.now();

        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            reportService.getReportsByAccount(numeroCuenta, initialDate, endDate);
        });
    }

    private static Account getAccount(String ownerId, Double initialBalance) {
        Account account = new Account();
        account.setState(EstadoCuenta.OPEN);
        account.setOwnerId(ownerId);
        account.setBalance(initialBalance);
        account.setType(TipoCuenta.SAVINGS_ACCOUNT);
        return account;
    }
}
