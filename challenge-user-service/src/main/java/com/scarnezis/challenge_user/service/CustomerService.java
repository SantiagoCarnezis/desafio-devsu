package com.scarnezis.challenge_user.service;

import com.scarnezis.challenge_user.dto.GetCustomer;
import com.scarnezis.challenge_user.entity.Customer;
import com.scarnezis.challenge_user.excepcion.ExistingClientException;
import com.scarnezis.challenge_user.excepcion.ClientNotFoundException;
import com.scarnezis.challenge_user.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, String> kafkaCloseAccount;
    private final String closeAccountTopic = "close-account-topic";


    public List<GetCustomer> getAllCustomers() {
        return customerRepository.findCustomer();
    }

    public GetCustomer getCustomerById(String id) {

        return customerRepository.findCustomer(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public void createCustomer(Customer customer) {

        boolean existe = customerRepository.existsById(customer.getId());

        if(existe)
            throw new ExistingClientException(customer.getId());

        customerRepository.save(customer);
    }

    public void updateCustomer(String id, Customer customerDto) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));

        customer.setName(customerDto.getName());
        customer.setGender(customerDto.isGender());
        customer.setAge(customerDto.getAge());
        customer.setDirection(customerDto.getDirection());
        customer.setPhone(customerDto.getPhone());
        customer.setPassword(customerDto.getPassword());
        customer.setState(customerDto.isState());

        customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {

        customerRepository.deleteById(id);
    }

    public void closeAccount(String numeroCuenta) {

        kafkaCloseAccount.send(closeAccountTopic, numeroCuenta);
    }
}

