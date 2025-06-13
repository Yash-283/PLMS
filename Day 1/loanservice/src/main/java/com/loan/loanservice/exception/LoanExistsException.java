package com.loan.loanservice.exception;

public class LoanExistsException extends Exception {
    public LoanExistsException(String msg) {
        super(msg);
    }
}
