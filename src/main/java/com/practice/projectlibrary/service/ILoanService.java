package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.LoanDTO;
import com.practice.projectlibrary.dto.request.LoanRequest;

import java.util.List;

public interface ILoanService {

    //get list loan
    List<LoanDTO> loans();

    //get list loan book of user

    //add new load

    LoanDTO addLoan(LoanRequest loanRequest);

    List<LoanDTO> addListLoan(List<LoanRequest> loanRequests);


    //update loan by slug && id
    LoanDTO updateLoan(Long id,LoanRequest loanRequests);


    //delete loan by slug && id
    LoanDTO deleteLoan(Long id);

    //user return book
    LoanDTO userToReturn(Long id);


}
