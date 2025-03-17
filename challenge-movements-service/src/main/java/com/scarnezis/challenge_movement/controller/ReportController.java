package com.scarnezis.challenge_movement.controller;

import com.scarnezis.challenge_movement.dto.GenericResponse;
import com.scarnezis.challenge_movement.dto.Messages;
import com.scarnezis.challenge_movement.dto.report.CustomerReport;
import com.scarnezis.challenge_movement.dto.report.AccountReport;
import com.scarnezis.challenge_movement.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/cliente")
    public GenericResponse getReportsByCustomer(@RequestParam String clienteId,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime initialDate,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime endDate) {

        CustomerReport customerReport = reportService.getReportsByCustomer(clienteId, initialDate, endDate);
        return GenericResponse.successfulResponse(Messages.REPORTS_FOUND, customerReport);
    }

    @GetMapping("/cuenta")
    public GenericResponse getReportesPorCuenta(@RequestParam String accountNumber,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime initialDate,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime endDate) {

        AccountReport accountReport = reportService.getReportsByAccount(accountNumber, initialDate, endDate);
        return GenericResponse.successfulResponse(Messages.REPORTS_FOUND, accountReport);
    }
}
