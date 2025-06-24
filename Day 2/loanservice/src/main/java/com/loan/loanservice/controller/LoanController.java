package com.loan.loanservice.controller;

import com.loan.loanservice.entity.Loan;
import com.loan.loanservice.exception.LoanExistsException;
import com.loan.loanservice.exception.LoanNotFoundException;
import com.loan.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping
    public ResponseEntity<?> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        System.out.println("Request served by loan-service running on port: " + port);
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<?> addLoan(@RequestBody Loan loan) throws LoanExistsException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(loan));
        } catch (LoanExistsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable("id") String id) throws LoanNotFoundException {
        try {
            return ResponseEntity.ok(loanService.getLoanById(id));
        } catch (LoanNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateLoan(@RequestBody Loan loan) throws LoanNotFoundException {
        try {
            return ResponseEntity.ok(loanService.updateLoan(loan));
        } catch (LoanNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLoan(@RequestParam("id") String id) throws LoanNotFoundException {
        try {
            loanService.deleteLoan(id);
            return ResponseEntity.ok("Loan deleted successfully");
        } catch (LoanNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

