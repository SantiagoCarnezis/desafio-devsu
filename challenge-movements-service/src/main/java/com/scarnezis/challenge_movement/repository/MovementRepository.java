package com.scarnezis.challenge_movement.repository;

import com.scarnezis.challenge_movement.dto.movement.GetMovement;
import com.scarnezis.challenge_movement.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, String> {

    List<Movement> findAllByAccount_AccountNumber(String accountNumber);

    @Query("SELECT new com.scarnezis.challenge_movement.dto.movement.GetMovement(m.id, m.date, m.customerId, m.type, m.amount, m.balance) " +
            "FROM Movement m " +
            "WHERE m.account.accountNumber = :accountNumber AND m.date >= :initialDate AND m.date <= :endDate " +
            "ORDER BY m.date")
    List<GetMovement> getReportsByAccount(@Param("accountNumber") String accountNumber,
                                          @Param("initialDate") LocalDateTime initialDate,
                                          @Param("endDate") LocalDateTime endDate);
    //Movement findFirstByCuentaNumeroCuentaAndFechaBeforeOrderByFechaDesc(String accountNumber, LocalDateTime date);
    Movement findFirstByAccount_AccountNumberAndDateBeforeOrderByDateDesc(String accountNumber, LocalDateTime date);
}

