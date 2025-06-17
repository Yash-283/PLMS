package com.loan.loanservice.repository;

import com.loan.loanservice.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
    // built-in CRUD methods available
}
