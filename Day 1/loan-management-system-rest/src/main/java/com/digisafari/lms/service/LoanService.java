package com.digisafari.lms.service;

import java.util.List;

import com.digisafari.lms.exception.LoanAlreadyExistsException;
import com.digisafari.lms.exception.LoanNotFoundException;
import com.digisafari.lms.model.Loan;

public interface LoanService {
	
	public List<Loan> getLoans();
	public Loan getLoanById(int id) throws LoanNotFoundException;
	public Loan addLoan(Loan loan) throws LoanAlreadyExistsException;
	public Loan updateLoan(Loan loan) throws LoanNotFoundException;	
	public boolean deleteLoan(int loanId) throws LoanNotFoundException;
	

}

