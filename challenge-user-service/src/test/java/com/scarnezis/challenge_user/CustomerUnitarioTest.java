package com.scarnezis.challenge_user;

import com.scarnezis.challenge_user.controller.CustomerController;
import com.scarnezis.challenge_user.dto.GenericResponse;
import com.scarnezis.challenge_user.dto.Messages;
import com.scarnezis.challenge_user.dto.GetCustomer;
import com.scarnezis.challenge_user.excepcion.ClientNotFoundException;
import com.scarnezis.challenge_user.feignCliente.Account;
import com.scarnezis.challenge_user.feignCliente.AccountService;
import com.scarnezis.challenge_user.feignCliente.EstadoCuenta;
import com.scarnezis.challenge_user.feignCliente.TipoCuenta;
import com.scarnezis.challenge_user.repository.CustomerRepository;
import com.scarnezis.challenge_user.service.CustomerService;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerUnitarioTest {

    @InjectMocks
    private CustomerController customerController;
    private CustomerService customerService;
    @Mock
    private AccountService accountService;
    @Mock
    private CustomerRepository customerRepository;
    private KafkaTemplate<String, String> kafkaCloseAccount;
    private final String customerId = "123";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerService(customerRepository, kafkaCloseAccount);
        customerController = new CustomerController(customerService, accountService);
    }

    @Test
    public void testCustomerWithAccounts() throws Exception {

        List<Account> accounts = new ArrayList<>();
        Account savingsAccount = new Account("123456", customerId,
                TipoCuenta.SAVINGS_ACCOUNT, 10.0, EstadoCuenta.OPEN);
        Account checkingAccount = new Account("654321", customerId,
                TipoCuenta.CHECKING_ACCOUNT, 10.0, EstadoCuenta.OPEN);
        accounts.add(savingsAccount);
        accounts.add(checkingAccount);

        GetCustomer customerWithAccounts = new GetCustomer(
                customerId, "santiago", true, 24,
                "direccion", "115323442", true, null);

        Mockito.when(customerRepository.findCustomer(customerId)).thenReturn(Optional.of(customerWithAccounts));

        Mockito.when(accountService.getAccounts(customerId))
                .thenReturn(ResponseEntity.ok(GenericResponse.successfulResponse("Respuesta exitosa", accounts)));

        GenericResponse response = customerController.getCustomerById(customerId);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getCode());
        Assertions.assertEquals(customerWithAccounts, response.getData());
        Assertions.assertFalse(((GetCustomer)response.getData()).getAccounts().isEmpty());
    }

    @Test
    public void testCustomerWithoutAccounts() throws Exception {

        GetCustomer customerWithoutAccounts = new GetCustomer(
                customerId, "santiago", true, 24,
                "direccion", "115323442", true, null);

        Mockito.when(customerRepository.findCustomer(customerId)).thenReturn(Optional.of(customerWithoutAccounts));

        Mockito.when(accountService.getAccounts(customerId))
                .thenReturn(ResponseEntity.ok(GenericResponse.successfulResponse("Respuesta exitosa", null)));

        GenericResponse response = customerController.getCustomerById(customerId);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getCode());
        Assertions.assertEquals(customerWithoutAccounts, response.getData());
        Assertions.assertTrue(response.getMessage().contains(Messages.CLIENT_FOUND_WITHOUT_ACCOUNTS));
        Assertions.assertNull(((GetCustomer)response.getData()).getAccounts());
    }

    @Test
    public void testConnectionError() throws Exception {

        GetCustomer customer = new GetCustomer(
                customerId, "santiago", true, 24,
                "direccion", "115323442", true, null);

        Mockito.when(customerRepository.findCustomer(customerId)).thenReturn(Optional.of(customer));

        Request request = Request.create(Request.HttpMethod.GET, "url", Map.of(), new byte[0], Charset.defaultCharset(), null);

        Mockito.when(accountService.getAccounts(customerId)).thenThrow(
                new FeignException.BadRequest("Error en la comunicacion con el microservicio de cuentas",
                        request, null, null));

        GenericResponse response = customerController.getCustomerById(customerId);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getCode());
        Assertions.assertEquals(customer, response.getData());
        Assertions.assertTrue(response.getErrors().contains(Messages.ACCOUNTS_NOT_RETRIEVED));
        Assertions.assertNull(((GetCustomer)response.getData()).getAccounts());
    }

    @Test
    public void testClienteInexistente() {

        Mockito.when(customerRepository.findCustomer(customerId))
                .thenThrow(new ClientNotFoundException(customerId));

        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> {
            customerController.getCustomerById(customerId);
        });

        Assertions.assertTrue(exception.getMessage().contains(Messages.CLIENT_NOT_FOUND + customerId));
    }
}

