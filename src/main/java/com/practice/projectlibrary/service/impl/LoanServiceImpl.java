package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.LoanMapper;
import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.entity.Loan;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ILoanRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.ILoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements ILoanService {
    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public List<LoanResponse> loans() {
        List<LoanResponse> loansDTO = new ArrayList<>();
        loanRepository.loans().stream().map(
                loan -> loansDTO.add(LoanMapper.getInstance().toResponse(loan))
        ).collect(Collectors.toList());

        return loansDTO;
    }


    @Override
    public LoanResponse addLoan(@Valid LoanRequest loanRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
        Loan loan = new Loan();
        loan = LoanMapper.getInstance().toEntity(loanRequest);
        loan.setBookId(loanRequest.getBookId());
//        loan.setUser(userRepository.findUserByEmail(authentication.getName());
        loan.setUser(userRepository.findUserById(loanRequest.getUserId()).get());
        loan.setActive(true);
        loan.setStatus("available");
        loan.setDateOfCheckout(dateCurrent);
        //3 days
        loan.setDataDue(new Timestamp(System.currentTimeMillis() + 259200000));
        loan.setDateReturned(null);
        loan.setDateOfCheckout(dateCurrent);
        loan.setCreatedBy("Librarian");


        return LoanMapper.getInstance().toResponse(loan);
    }

    @Override
    public List<LoanResponse> addListLoan(List<@Valid LoanRequest> loanRequests) {
        List<LoanResponse> loansDTO = new ArrayList<>();
        Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
        for (LoanRequest loanRequest : loanRequests) {
            Loan loan = new Loan();
            loan.setBookId(loanRequest.getBookId());
            loan.setUser(userRepository.findUserById(loanRequest.getUserId()).get());
            loan.setQuantity(loanRequest.getQuantity());
            loan.setActive(true);
            loan.setStatus("available");
            loan.setDateOfCheckout(dateCurrent);
            //3 days
            loan.setDataDue(new Timestamp(System.currentTimeMillis() + 259200000));
            loan.setDateReturned(null);
            loan.setDateOfCheckout(dateCurrent);
            loan.setCreatedBy("Librarian");
            loanRepository.save(loan);
            loansDTO.add(LoanMapper.getInstance().toResponse(loan));
        }

        return loansDTO;
    }

    @Override
    public LoanResponse updateLoan(Long id, LoanRequest loanRequests) {


        return null;
    }


    @Override
    public LoanResponse deleteLoan(Long id) {
        Optional<Loan> currentLoan = Optional.of(loanRepository.getReferenceById(id));

        if (currentLoan.isPresent()) {
            currentLoan.get().setActive(false);
            currentLoan.get().setStatus("removed");
            return LoanMapper.getInstance().toResponse(currentLoan.get());

        } else {
            throw new NotFoundException("Loan not found by id");
        }

    }


    //user return book
    @Override
    public LoanResponse userToReturn(Long id) {
        Optional<Loan> currentLoan = Optional.of(loanRepository.getReferenceById(id));

        if (currentLoan.isPresent()) {
            currentLoan.get().setActive(false);
            currentLoan.get().setStatus("returned");
            currentLoan.get().setDateReturned(new Timestamp(System.currentTimeMillis()));
            return LoanMapper.getInstance().toResponse(currentLoan.get());
        } else {
            throw new NotFoundException("Loan not found by id");
        }
    }
}
