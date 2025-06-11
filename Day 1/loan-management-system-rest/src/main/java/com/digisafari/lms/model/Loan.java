package com.digisafari.lms.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
	
	
	private int id;
	private String applicantName;
	private String applicantEmail;
	private long applicantContactNo;
	private double loanAmount;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date appliedDate;
	private String status;

}

