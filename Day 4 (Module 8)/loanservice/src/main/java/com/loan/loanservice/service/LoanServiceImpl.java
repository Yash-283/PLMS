package com.loan.loanservice.service;

import com.loan.loanservice.entity.Loan;
import com.loan.loanservice.exception.LoanExistsException;
import com.loan.loanservice.exception.LoanNotFoundException;
import com.loan.loanservice.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan createLoan(Loan loan) throws LoanExistsException {
        Optional<Loan> existingLoan = loanRepository.findById(loan.getId());
        if (existingLoan.isPresent()) {
            throw new LoanExistsException("Loan already exists with id " + loan.getId());
        }
        loan.setStatus("Pending");
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(String id) throws LoanNotFoundException {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id " + id));
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Loan loan) throws LoanNotFoundException {
        if (!loanRepository.existsById(loan.getId())) {
            throw new LoanNotFoundException("Loan not found with id " + loan.getId());
        }
        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(String id) throws LoanNotFoundException {
        if (!loanRepository.existsById(id)) {
            throw new LoanNotFoundException("Loan not found with id " + id);
        }
        loanRepository.deleteById(id);
    }
}
