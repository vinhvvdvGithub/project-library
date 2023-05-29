package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

  @Autowired
  private ILoanService loanService;

  @GetMapping("/")
  public List<LoanResponse> loans() {
    return loanService.loans();
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public LoanResponse addLoan(@RequestBody @Valid LoanRequest loanRequest) {
    return loanService.addLoan(loanRequest);
  }

  @PostMapping("/add-list-loan")
  @ResponseStatus(HttpStatus.OK)
  public List<LoanResponse> addListLoan(@RequestBody List<LoanRequest> loanRequest) {
    return loanService.addListLoan(loanRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public LoanResponse deleteLoan(@PathVariable("id") @Positive(message = "Id must greater than zero") Long id) {
    return loanService.deleteLoan(id);
  }

  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public LoanResponse userToReturn(@PathVariable("id") @Positive(message = "Id  must greater than zero") Long id) {
    return loanService.userToReturn(id);
  }


}
