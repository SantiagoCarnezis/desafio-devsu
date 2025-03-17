package com.scarnezis.challenge_user.repository;

import com.scarnezis.challenge_user.dto.GetCustomer;
import com.scarnezis.challenge_user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT new com.scarnezis.challenge_user.dto.GetCustomer(" +
            "c.id, c.name, c.gender, c.age, c.direction, c.phone, c.state) FROM Customer c")
    List<GetCustomer> findCustomer();

    @Query("SELECT new com.scarnezis.challenge_user.dto.GetCustomer(" +
            "c.id, c.name, c.gender, c.age, c.direction, c.phone, c.state) " +
            "FROM Customer c WHERE c.id = :id")
    Optional<GetCustomer> findCustomer(String id);
}
