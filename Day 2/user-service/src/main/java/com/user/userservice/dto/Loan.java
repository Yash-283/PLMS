package com.user.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    private String id;
    private String applicantName;
    private String applicantEmail;
    private long applicantContactNo;
    private double loanAmount;
    private int tenure;
    private String status;
}
