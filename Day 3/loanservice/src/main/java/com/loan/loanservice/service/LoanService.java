package com.loan.loanservice.service;

import com.loan.loanservice.entity.Loan;
import com.loan.loanservice.exception.LoanExistsException;
import com.loan.loanservice.exception.LoanNotFoundException;

import java.util.List;

public interface LoanService {

    Loan createLoan(Loan loan) throws LoanExistsException;
    Loan getLoanById(String id) throws LoanNotFoundException;
    List<Loan> getAllLoans();
    Loan updateLoan(Loan loan) throws LoanNotFoundException;
    void deleteLoan(String id) throws LoanNotFoundException;
}
