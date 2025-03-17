package com.scarnezis.challenge_movement.repository;

import com.scarnezis.challenge_movement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAllByOwnerId(String clienteId);

    @Query("SELECT c.accountNumber FROM Account c WHERE c.ownerId = :owner")
    List<String> findAccountNumberByOwnerId(@Param("owner") String owner);
}

