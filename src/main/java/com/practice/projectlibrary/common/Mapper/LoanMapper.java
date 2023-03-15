package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.LoanDTO;
import com.practice.projectlibrary.dto.request.LoanRequest;
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
    public Loan toEntity(LoanRequest loanRequest){
        Loan loan = new Loan();
        loan.setBookId(loanRequest.getBookId());
        loan.setQuantity(loanRequest.getQuantity());
        return loan;

    }

    //to Entity from dto
    public Loan toEntity(LoanDTO loanDTO){
        Loan loan = new Loan();
        loan.setBookId(loanDTO.getBookId());
        loan.setQuantity(loanDTO.getQuantity());
        loan.setActive(loanDTO.getActive());
        loan.setStatus(loanDTO.getStatus());
        loan.setDateOfCheckout(loanDTO.getDateOfCheckout());
        loan.setDataDue(loanDTO.getDataDue());
        loan.setDateReturned(loanDTO.getDateReturned());
        return loan;

    }

    //to DTO

    public LoanDTO toDTO(Loan loan){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setBookId(loan.getBookId());
        loanDTO.setUserId(loan.getUser().getId());
        loanDTO.setQuantity(loan.getQuantity());
        loanDTO.setActive(loan.getActive());
        loanDTO.setStatus(loan.getStatus());
        loanDTO.setDateOfCheckout(loan.getDateOfCheckout());
        loanDTO.setDataDue(loan.getDataDue());
        loanDTO.setDateReturned(loan.getDateReturned());

        return loanDTO;

    }

}
