package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

	private final ILoanService loanService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<LoanResponse> loans() {
		return loanService.loans();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LoanResponse loanDetail(@PathVariable("id") Long id) {
		return loanService.loanDetailById(id);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public LoanResponse addLoan(@RequestBody @Valid LoanRequest loanRequest) {
		return loanService.addLoan(loanRequest);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LoanResponse deleteLoan(@PathVariable("id") @Positive(message = "Id must greater than zero") Long id) {
		return loanService.deleteLoan(id);
	}


	//user trả sách
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LoanResponse toReturn(@PathVariable("id") @Positive(message = "Id  must greater than zero") Long id) {
		return loanService.toReturn(id);
	}

	//user renew loan
	@PutMapping("/renew/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LoanResponse toRenew(@PathVariable("id") @Positive(message = "Id  must greater than zero") Long id){
		return loanService.toRenew(id);
	}





}
