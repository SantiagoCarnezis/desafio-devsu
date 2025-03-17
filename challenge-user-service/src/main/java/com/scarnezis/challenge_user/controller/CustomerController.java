package com.scarnezis.challenge_user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scarnezis.challenge_user.dto.GenericResponse;
import com.scarnezis.challenge_user.dto.Messages;
import com.scarnezis.challenge_user.dto.GetCustomer;
import com.scarnezis.challenge_user.entity.Customer;
import com.scarnezis.challenge_user.feignCliente.AccountService;
import com.scarnezis.challenge_user.feignCliente.Account;
import com.scarnezis.challenge_user.service.CustomerService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public GenericResponse getAllCustomer() {

        List<GetCustomer> clients = customerService.getAllCustomers();
        return GenericResponse.successfulResponse(Messages.CLIENTS_FOUND, clients);
    }

    @GetMapping("/{id}")
    public GenericResponse getCustomerById(@PathVariable String id) {

        GenericResponse response = null;
        GetCustomer customer = customerService.getCustomerById(id);

        try {

            ResponseEntity<GenericResponse> responseFeignClient = accountService.getAccounts(id);

            if (responseFeignClient.getBody() != null){

                String accountsString = mapper.writeValueAsString(responseFeignClient.getBody().getData());
                List<Account> account = mapper.readValue(accountsString, new TypeReference<>() {});

                if (account != null && !account.isEmpty()) {
                    customer.setAccounts(account);
                    response = GenericResponse.successfulResponse(Messages.CLIENT_FOUND, customer);
                }
                else
                    response = GenericResponse.successfulResponse(Messages.CLIENT_FOUND_WITHOUT_ACCOUNTS, customer);
            }
            else
                response = GenericResponse.successfulResponse(Messages.CLIENT_FOUND_WITHOUT_ACCOUNTS, customer);
        }
        catch (ConnectException | FeignException ex){

            response = GenericResponse.successfulResponse(Messages.CLIENT_FOUND, customer);
            response.addError(Messages.ACCOUNTS_NOT_RETRIEVED);
            logger.error(Messages.ACCOUNT_SERVICE_CONNECTION_ERROR + id);
        }
        catch (JsonProcessingException e) {
            response = GenericResponse.successfulResponse(Messages.CLIENT_FOUND, customer);
            response.addError(Messages.ACCOUNTS_NOT_RETRIEVED);
            logger.error(Messages.ERROR_RETRIEVING_ACCOUNTS + id);
        }

        return response;
    }

    @PostMapping
    public GenericResponse createCustomer(@RequestBody Customer customerDto) {
        customerService.createCustomer(customerDto);
        return GenericResponse.successfulResponse(Messages.CLIENT_CREATED, null);
    }

    @PutMapping("/{id}")
    public GenericResponse updateCustomer(@PathVariable String id, @RequestBody Customer customerDto) {
        customerService.updateCustomer(id, customerDto);
        return GenericResponse.successfulResponse(Messages.CLIENT_UPDATED, null);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return GenericResponse.successfulResponse(Messages.CLIENT_DELETED, null);
    }

    @PostMapping("/cerrar-cuenta/{accountNumber}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public GenericResponse closeAccount(@PathVariable String accountNumber) {
        customerService.closeAccount(accountNumber);
        GenericResponse response = GenericResponse.successfulResponse(Messages.ACCOUNT_CLOSED, null);
        response.setCode(202);
        return response;
    }
}
