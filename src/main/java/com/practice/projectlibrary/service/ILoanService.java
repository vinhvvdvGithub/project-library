package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;

import java.util.List;

public interface ILoanService {

    //get list loan
    List<LoanResponse> loans();

    //get list loan book of user

    //add new load

    LoanResponse addLoan(LoanRequest loanRequest);

    List<LoanResponse> addListLoan(List<LoanRequest> loanRequests);


    //update loan by slug && id
    LoanResponse updateLoan(Long id,LoanRequest loanRequests);


    //delete loan by slug && id
    LoanResponse deleteLoan(Long id);

    //user return book
    LoanResponse userToReturn(Long id);


}
