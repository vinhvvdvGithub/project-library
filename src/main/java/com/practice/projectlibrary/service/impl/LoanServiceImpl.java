package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.LoanMapper;
import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.entity.Loan;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ILoanRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.ILoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

  private final ILoanRepository loanRepository;
  private final IUserRepository userRepository;
  public String userAuth;

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

    userAuth = SecurityContextHolder.getContext().getAuthentication().getName();
    boolean userLogged = userRepository.existsUserByEmail(userAuth);


    if (userLogged) {
      Optional<User> userExist = userRepository.findUserByEmail(userAuth);

      if (userExist.isPresent() && userExist.get().getActive() == true) {
        Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
        Loan loan = LoanMapper.getInstance().toEntity(loanRequest);
        loan.setBookId(loanRequest.getBookId());
        loan.setUser(userExist.get());
        loan.setActive(true);
        loan.setStatus("available");
        loan.setDateOfCheckout(dateCurrent);
        //3 days
        loan.setDataDue(new Timestamp(System.currentTimeMillis() + 259200000));
        loan.setDateReturned(null);
        loan.setDateOfCheckout(dateCurrent);
        loan.setCreatedBy(userAuth);

        loanRepository.save(loan);

        return LoanMapper.getInstance().toResponse(loan);
      } else {
        throw new NotFoundException("User not found or deleted, check it out");
      }
    } else {
      throw new NotFoundException("Please login!!!");
    }


  }

  @Override
  public List<LoanResponse> addListLoan(List<@Valid LoanRequest> loanRequests) {
    List<LoanResponse> loansDTO = new ArrayList<>();
    Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
    for (LoanRequest loanRequest : loanRequests) {
      Loan loan = new Loan();
      loan.setBookId(loanRequest.getBookId());
//      loan.setUser(userRepository.findUserById(loanRequest.getUserId()).get());
      loan.setUser(null);
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
//    return null;
  }

  @Override
  public LoanResponse updateLoan(Long id, LoanRequest loanRequests) {


    return null;
  }


  @Override
  public LoanResponse deleteLoan(Long id) {
    Optional<Loan> currentLoan = loanRepository.getLoanById(id);

    if (currentLoan.isPresent()) {
      currentLoan.get().setActive(false);
      currentLoan.get().setStatus("removed");
      currentLoan.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
      return LoanMapper.getInstance().toResponse(currentLoan.get());

    } else {
      throw new NotFoundException("Loan not found by id");
    }

  }


  //user return book đúng hạn
  @Override
  public LoanResponse userToReturn(Long id) {
    userAuth = SecurityContextHolder.getContext().getAuthentication().getName();
    if (userAuth.isEmpty()) {
      throw new NotFoundException("Vui lòng đăng nhập...!");
    } else {
      Optional<Loan> currentLoan = loanRepository.getLoanById(id);
      if (currentLoan.isPresent() && currentLoan.get().getUser().getEmail().equals(userAuth)) {
        currentLoan.get().setActive(false);
        currentLoan.get().setStatus(userAuth + "has returned");
        currentLoan.get().setDateReturned(new Timestamp(System.currentTimeMillis()));
        currentLoan.get().setUpdatedBy(userAuth);
        loanRepository.save(currentLoan.get());
        return LoanMapper.getInstance().toResponse(currentLoan.get());
      } else {
        throw new NotFoundException("Không tìm thấy sách muượn hoặc user không đúng");
      }
    }

  }
}
