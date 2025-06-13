package com.loan.loanservice.repository;

import com.loan.loanservice.model.Loan;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LoanRepository {

    private final List<Loan> loansList = new ArrayList<>();

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

    public Loan updateLoan(Loan loan) {
        Loan existingLoan = getLoanById(loan.getId()).orElse(null);
        if (existingLoan != null) {
            int index = this.loansList.indexOf(existingLoan);
            this.loansList.set(index, loan);
        }
        return loan;
    }

    public boolean deleteLoan(int id) {
        return this.loansList.removeIf(loan -> loan.getId() == id);
    }
}
