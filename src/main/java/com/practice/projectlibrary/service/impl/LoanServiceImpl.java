package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.LoanMapper;
import com.practice.projectlibrary.common.Schedule.SchedulingTask;
import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.entity.EmailDetails;
import com.practice.projectlibrary.entity.Loan;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ILoanRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.ILoanService;
import com.practice.projectlibrary.service.IMailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableScheduling
//@Transactional
public class LoanServiceImpl implements ILoanService {

	private static final Logger log = LoggerFactory.getLogger(SchedulingTask.class);

	private final ILoanRepository loanRepository;
	private final IUserRepository userRepository;
	private final IMailService mailService;
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
	public LoanResponse loanDetailById(Long id) {
		Optional<Loan> detailLoan = loanRepository.selectLoanById(id);
		if (detailLoan.isPresent()) {
			return LoanMapper.getInstance().toResponse(detailLoan.get());
		} else {
			throw new NotFoundException("Not found loan by Id");
		}
	}


	@Override
	public LoanResponse addLoan(@Valid LoanRequest loanRequest) {

		userAuth = SecurityContextHolder.getContext().getAuthentication().getName();


		Optional<User> userExist = userRepository.findUserByEmail(userAuth);

		if (userExist.isPresent() && userExist.get().getActive() == true) {
			Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
			Loan loan = LoanMapper.getInstance().toEntity(loanRequest);
			loan.setBookId(loanRequest.getBookId());
			loan.setUser(userExist.get());
			loan.setActive(true);
			loan.setStatus("Check out");
			loan.setDateOfCheckout(dateCurrent);
			//3 days
			loan.setDataDue(new Timestamp(System.currentTimeMillis() + 259200000));
			loan.setDateReturned(null);
			loan.setDateOfCheckout(dateCurrent);
			loan.setCreatedBy(userAuth);

			loanRepository.save(loan);

			return LoanMapper.getInstance().toResponse(loan);
		} else {
			throw new NotFoundException("User not found or inactive, check it out");
		}


	}


	@Override
	public LoanResponse updateLoan(Long id, LoanRequest loanRequests) {
		Optional<Loan> currentLoan = loanRepository.selectLoanById(id);


		return null;
	}


	@Override
	public LoanResponse deleteLoan(Long id) {
		Optional<Loan> currentLoan = loanRepository.selectLoanById(id);
		if (currentLoan.isPresent()) {
			currentLoan.get().setActive(false);
			currentLoan.get().setStatus("removed");
			currentLoan.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			return LoanMapper.getInstance().toResponse(currentLoan.get());

		} else {
			throw new NotFoundException("Loan not found by id");
		}

	}


	//user trả book đúng hạn
	@Override
	@Modifying
	public LoanResponse userToReturn(Long id) {
		userAuth = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<Loan> currentLoan = loanRepository.selectLoanById(id);
		if (currentLoan.isPresent() && currentLoan.get().getUser().getEmail().equals(userAuth)) {
			currentLoan.get().setActive(false);
			currentLoan.get().setStatus("Check in");
			currentLoan.get().setDateReturned(new Timestamp(System.currentTimeMillis()));
			currentLoan.get().setUpdatedBy(userAuth);
			log.info("updated userToReturnBook");
			try {
				loanRepository.save(currentLoan.get());

			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			return LoanMapper.getInstance().toResponse(currentLoan.get());
		} else {
			throw new NotFoundException("Không tìm thấy sách muượn hoặc user không đúng");
		}


	}


	//scheduling 12h check time returned
	@Scheduled(fixedRate = 43200000)
	@Transactional
	public void updateTimeExpired() {
		Timestamp dateNow = new Timestamp(System.currentTimeMillis());
		List<Loan> loans = loanRepository.loans();
//		handle time expired
		loans.forEach(loan -> {
			if(loan.getDateReturned() != null){
				if (dateNow.after(loan.getDataDue())) {
					User user = userRepository.getReferenceById(loan.getUser().getId());
					log.info("User Id: " + loan.getUser().getId() + " trễ hẹn ngày trả sách");
					loan.setStatus("trễ hẹn ngày trả sách");
					loanRepository.save(loan);
					//send email
					EmailDetails emailNotification = new EmailDetails();
					emailNotification.setSubject("Đã quá ngày mượn sách");
					emailNotification.setRecipient(user.getEmail());

					emailNotification.setMsgBody(mailService.templateEmailNotification(loan.getUser().getUsername()));
					mailService.sendEmailNotification(emailNotification);
				}
			}
		});

		log.info("update time expired");
	}

}
