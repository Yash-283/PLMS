package com.digisafari.lms.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.digisafari.lms.model.Loan;

@Repository
public class LoanRepository {

	private List<Loan> loansList;;

	public LoanRepository() {
		this.loansList = new ArrayList<>();
	}

	public List<Loan> getLoans() {
		return this.loansList;
	}

	public Optional<Loan> getLoanById(int id) {
		return this.loansList.stream().filter(loan -> loan.getId() == id).findFirst();
	}

	public Loan addLoan(Loan loan) {
		this.loansList.add(loan);
		return loan;
	}

	public Loan updateLoan(Loan loanToBeUpdated) {
		Loan loanById = this.loansList.stream().filter(loan -> loan.getId() == loanToBeUpdated.getId()).findFirst()
				.get();
		int index = this.loansList.indexOf(loanById);
		this.loansList.set(index, loanToBeUpdated);
		return loanToBeUpdated;

	}

	public boolean deleteLoan(int loanId) {
		boolean status = false;
		Loan loanById = this.loansList.stream().filter(loan -> loan.getId() == loanId).findFirst().get();
		int index = this.loansList.indexOf(loanById);
		this.loansList.remove(index);
		status = true;
		return status;

	}

}
