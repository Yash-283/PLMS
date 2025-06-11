package com.digisafari.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digisafari.lms.exception.LoanAlreadyExistsException;
import com.digisafari.lms.exception.LoanNotFoundException;
import com.digisafari.lms.model.Loan;
import com.digisafari.lms.service.LoanServiceImpl;

@RestController
@RequestMapping("api/v1")
public class LoanController {

	@Autowired
	private LoanServiceImpl loanService;
	
	@PostMapping("/loans")
	public ResponseEntity<?> addLoan(@RequestBody Loan loan){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(loanService.addLoan(loan));
		} catch (LoanAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());			
		}
	}
	
	@GetMapping("/loans")
	public ResponseEntity<?> getLoans(){
		return ResponseEntity.ok(loanService.getLoans());
	}
	
	@GetMapping("/loans/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable("id") int id){
		try {
			return ResponseEntity.ok(loanService.getLoanById(id));
		} catch(LoanNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		}
	}
	
	@PutMapping("/loans")
	public ResponseEntity<?> updateLoan(@RequestBody Loan loan){
		try {
			return ResponseEntity.ok(loanService.updateLoan(loan));
		} catch(LoanNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		}
	}
	
	@DeleteMapping("/loans/{id}")
	public ResponseEntity<?> deleteLoan(@PathVariable("id") int id){
		try {
			return ResponseEntity.ok(loanService.deleteLoan(id));
		} catch(LoanNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		}
	}
	
	
}
