package com.loan.loanservice.controller;

import com.loan.loanservice.exception.LoanExistsException;
import com.loan.loanservice.exception.LoanNotFoundException;
import com.loan.loanservice.model.Loan;

import com.loan.loanservice.service.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoanController {

    @Autowired
    private LoanServiceImpl loanService;

    @PostMapping("/loans")
    public ResponseEntity<?> addLoan(@RequestBody Loan loan) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(loanService.addLoan(loan));
        } catch (LoanExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/loans")
    public ResponseEntity<?> getLoans() {
        return ResponseEntity.ok(loanService.getLoans());
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(loanService.getLoanById(id));
        } catch (LoanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/loans")
    public ResponseEntity<?> updateLoan(@RequestBody Loan loan) {
        try {
            return ResponseEntity.ok(loanService.updateLoan(loan));
        } catch (LoanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable int id) {
        try {
            return ResponseEntity.ok(loanService.deleteLoan(id));
        } catch (LoanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
