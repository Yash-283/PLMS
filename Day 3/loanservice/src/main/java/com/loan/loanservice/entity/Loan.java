package com.loan.loanservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loan_details")
public class Loan {
    @Id
    private String id;

    private String applicantName;
    private String applicantEmail;
    private long applicantContactNo;
    private double loanAmount;
    private int tenure;
    private String status;
}
