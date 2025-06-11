package com.digisafari.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisafari.lms.exception.LoanAlreadyExistsException;
import com.digisafari.lms.exception.LoanNotFoundException;
import com.digisafari.lms.model.Loan;
import com.digisafari.lms.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	public LoanServiceImpl() {
	}

	@Override
	public List<Loan> getLoans() {
		return this.loanRepository.getLoans();
	}
	
	@Override
	public Loan getLoanById(int id) throws LoanNotFoundException {
		Optional<Loan> loanByIdOptional = this.loanRepository.getLoanById(id);
		System.out.println("Loan :" + loanByIdOptional);
		if (!loanByIdOptional.isPresent())
			throw new LoanNotFoundException("Loan Not Found With this Id :" + id);
		else {
			return loanByIdOptional.get();
		}
	}


	@Override
	public Loan addLoan(Loan loan) throws LoanAlreadyExistsException {
		Optional<Loan> loanByIdOptional = this.loanRepository.getLoanById(loan.getId());
		System.out.println("Loan :" + loanByIdOptional);
		if (loanByIdOptional.isPresent())
			throw new LoanAlreadyExistsException("Loan Already Exists With this Id :" + loan.getId());
		else {
			loan.setStatus("Pending");
			return this.loanRepository.addLoan(loan);
		}

	}

	@Override
	public Loan updateLoan(Loan loan) throws LoanNotFoundException {
		Optional<Loan> loanByIdOptional = this.loanRepository.getLoanById(loan.getId());
		System.out.println("Loan :" + loanByIdOptional);
		if (!loanByIdOptional.isPresent())
			throw new LoanNotFoundException("Loan Not Found With this Id :" + loan.getId());
		else {			
			return this.loanRepository.updateLoan(loan);
		}
	}

	

	@Override
	public boolean deleteLoan(int loanId) throws LoanNotFoundException {

		Optional<Loan> loanByIdOptional = this.loanRepository.getLoanById(loanId);
		System.out.println("Loan :" + loanByIdOptional);
		if (!loanByIdOptional.isPresent())
			throw new LoanNotFoundException("Loan Not Found With this Id :" + loanId);
		else {
			return this.loanRepository.deleteLoan(loanId);
		}
	}

	
}
