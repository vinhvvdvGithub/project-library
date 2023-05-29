package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.entity.Loan;

public class LoanMapper {

  private static LoanMapper INSTANCE;

  public static LoanMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new LoanMapper();
    }
    return INSTANCE;
  }


  //to Entity from request
  public Loan toEntity(LoanRequest loanRequest) {
    Loan loan = new Loan();
    loan.setBookId(loanRequest.getBookId());
    loan.setQuantity(loanRequest.getQuantity());
    return loan;

  }

  //to loan response
  public LoanResponse toResponse(Loan loan) {
    LoanResponse loanResponse = new LoanResponse();
    loanResponse.setBookId(loan.getBookId());
    loanResponse.setUserId(loan.getUser().getId());
    loanResponse.setQuantity(loan.getQuantity());
    loanResponse.setActive(loan.getActive());
    loanResponse.setStatus(loan.getStatus());
    loanResponse.setDateOfCheckout(loan.getDateOfCheckout());
    loanResponse.setDataDue(loan.getDataDue());
    loanResponse.setDateReturned(loan.getDateReturned());

    return loanResponse;

  }

}
