package com.loan.loanservice.service;

import com.loan.loanservice.exception.LoanExistsException;
import com.loan.loanservice.exception.LoanNotFoundException;
import com.loan.loanservice.model.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> getLoans();
    Loan getLoanById(int id) throws LoanNotFoundException;
    Loan addLoan(Loan loan) throws LoanExistsException;
    Loan updateLoan(Loan loan) throws LoanNotFoundException;
    boolean deleteLoan(int id) throws LoanNotFoundException;
}
