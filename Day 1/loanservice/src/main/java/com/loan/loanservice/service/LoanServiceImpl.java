package com.loan.loanservice.service;

import com.loan.loanservice.exception.LoanExistsException;
import com.loan.loanservice.exception.LoanNotFoundException;
import com.loan.loanservice.model.Loan;

import com.loan.loanservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> getLoans() {
        return loanRepository.getLoans();
    }

    @Override
    public Loan getLoanById(int id) throws LoanNotFoundException {
        return loanRepository.getLoanById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not Found With this Id :" + id));
    }

    @Override
    public Loan addLoan(Loan loan) throws LoanExistsException {
        Optional<Loan> existing = loanRepository.getLoanById(loan.getId());
        if (existing.isPresent()) {
            throw new LoanExistsException("Loan Already Exists With this Id :" + loan.getId());
        }
        loan.setStatus("Pending");
        return loanRepository.addLoan(loan);
    }

    @Override
    public Loan updateLoan(Loan loan) throws LoanNotFoundException {
        Optional<Loan> existing = loanRepository.getLoanById(loan.getId());
        if (existing.isEmpty()) {
            throw new LoanNotFoundException("Loan Not Found With this Id :" + loan.getId());
        }
        return loanRepository.updateLoan(loan);
    }

    @Override
    public boolean deleteLoan(int id) throws LoanNotFoundException {
        Optional<Loan> existing = loanRepository.getLoanById(id);
        if (existing.isEmpty()) {
            throw new LoanNotFoundException("Loan Not Found With this Id :" + id);
        }
        return loanRepository.deleteLoan(id);
    }
}
