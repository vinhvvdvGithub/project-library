package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.LoanMapper;
import com.practice.projectlibrary.dto.LoanDTO;
import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.respone.LoanRespone;
import com.practice.projectlibrary.entity.Loan;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ILoanRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl  implements ILoanService{
    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private IUserRepository userRepository;


    @Override
    public List<LoanDTO> loans() {
        List<LoanDTO> loansDTO = new ArrayList<>();
         loanRepository.loans().stream().map(
                loan -> loansDTO.add(LoanMapper.getInstance().toDTO(loan))
        ).collect(Collectors.toList());

        return loansDTO;
    }



    @Override
    public LoanDTO addLoan(LoanRequest loanRequest) {
        LoanDTO loanDTO;
        Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
        Loan loan = new Loan();
        loan.setBookId(loanRequest.getBookId());
        loan.setUser( userRepository.findUserById(loanRequest.getUserId()).get());
        loan.setQuantity(loanRequest.getQuantity());
        loan.setActive(true);
        loan.setStatus("available");
        loan.setDateOfCheckout(dateCurrent);
        //3 days
        loan.setDataDue(new Timestamp(System.currentTimeMillis()+259200000));
        loan.setDateReturned(null);
        loan.setDateOfCheckout(dateCurrent);
        loan.setCreatedBy("Librarian");

        loanDTO = LoanMapper.getInstance().toDTO(loan);

        return loanDTO;
    }

    @Override
    public List<LoanDTO> addListLoan(List<LoanRequest> loanRequests) {
        List<LoanDTO> loansDTO= new ArrayList<>();
        Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
        for (LoanRequest loanRequest : loanRequests){
            Loan loan = new Loan();
            loan.setBookId(loanRequest.getBookId());
            loan.setUser( userRepository.findUserById(loanRequest.getUserId()).get());
            loan.setQuantity(loanRequest.getQuantity());
            loan.setActive(true);
            loan.setStatus("available");
            loan.setDateOfCheckout(dateCurrent);
            //3 days
            loan.setDataDue(new Timestamp(System.currentTimeMillis()+259200000));
            loan.setDateReturned(null);
            loan.setDateOfCheckout(dateCurrent);
            loan.setCreatedBy("Librarian");
            loanRepository.save(loan);
            loansDTO.add(LoanMapper.getInstance().toDTO(loan));
        }

        return loansDTO;
    }

    @Override
    public LoanDTO updateLoan( Long id, LoanRequest loanRequests) {



        return null;
    }


    @Override
    public LoanDTO deleteLoan(Long id) {
        Optional<Loan> currentLoan = Optional.of(loanRepository.getReferenceById(id));
        LoanDTO loanDTO;
        if(currentLoan.isPresent()){
            currentLoan.get().setActive(false);
            currentLoan.get().setStatus("removed");
            loanDTO= LoanMapper.getInstance().toDTO(currentLoan.get());
            return loanDTO;
        }else{
            throw new NotFoundException("Loan not found by id");
        }

    }


    //user return book
    @Override
    public LoanDTO userToReturn(Long id) {
        Optional<Loan> currentLoan = Optional.of(loanRepository.getReferenceById(id));
        LoanDTO loanDTO;
        if(currentLoan.isPresent()){
            currentLoan.get().setActive(false);
            currentLoan.get().setStatus("returned");
            currentLoan.get().setDateReturned(new Timestamp(System.currentTimeMillis()));
            loanDTO= LoanMapper.getInstance().toDTO(currentLoan.get());
            return loanDTO;
        }else{
            throw new NotFoundException("Loan not found by id");
        }
    }
}
