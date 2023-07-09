package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.LoanMapper;
import com.practice.projectlibrary.common.Schedule.SchedulingTask;
import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.response.LoanResponse;
import com.practice.projectlibrary.entity.EmailDetails;
import com.practice.projectlibrary.entity.Loan;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.exception.RenewException;
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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
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

	//time handle for now
	public static ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
	public static LocalDateTime now = LocalDateTime.now();

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
			Loan loan = LoanMapper.getInstance().toEntity(loanRequest);
			loan.setBookId(loanRequest.getBookId());
			loan.setUser(userExist.get());
			loan.setActive(true);
			loan.setStatus("Check out");

			ZonedDateTime dateOfCheckout = now.atZone(zoneId);
			loan.setDateOfCheckout(Timestamp.from(dateOfCheckout.toInstant()));
			log.info("setDateOfCheckout: " + Timestamp.from(dateOfCheckout.toInstant()));
			//3 days

			loan.setDueDate(Timestamp.from(now.plusDays(3).atZone(zoneId).toInstant()));
			log.info("setDueDate: " + Timestamp.from(now.plusDays(3).atZone(zoneId).toInstant()));
			loan.setDateReturned(null);
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
	public LoanResponse toReturn(Long id) {
		userAuth = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<Loan> currentLoan = loanRepository.selectLoanById(id);
		if (currentLoan.isPresent() && currentLoan.get().getUser().getEmail().equals(userAuth)) {
			currentLoan.get().setActive(false);
			currentLoan.get().setStatus("Check in");
			currentLoan.get().setDateReturned(new Timestamp(System.currentTimeMillis()));
			currentLoan.get().setUpdatedBy(userAuth);
			loanRepository.save(currentLoan.get());
			log.info("updated userToReturnBook");

			return LoanMapper.getInstance().toResponse(currentLoan.get());
		} else {
			log.info("Loan Id "+ id + "not found");
			throw new NotFoundException("Không tìm thấy!!!");
		}


	}

	@Override
	public LoanResponse toRenew(Long id) {

		Optional<Loan> currentLoan = loanRepository.selectLoanById(id);

		//check active
		if (currentLoan.isPresent()) {
			LocalDateTime updatedTime = currentLoan.get().getUpdatedDate().toLocalDateTime();
			int logRenewTime = currentLoan.get().getLogRenew();

			//check log renew time
			if (logRenewTime > 3) {
				throw new RenewException("Đã quá số lần gia hạn sách");
			}

			//check renew in once day
			if (now.getDayOfMonth() == updatedTime.getDayOfMonth()) {
				throw new RenewException("Bạn đã gia hạn sách trong ngày hôm nay");
			}

			LocalDateTime oldDueDate = currentLoan.get().getDueDate().toLocalDateTime();
			log.info("oldDueDateTimestamp " + oldDueDate);

			LocalDateTime newDueDate = oldDueDate.plusDays(3);

			ZonedDateTime dueDateNew = newDueDate.atZone(zoneId);
			log.info("dueDateNew " + dueDateNew);


			currentLoan.get().setDueDate(Timestamp.from(dueDateNew.toInstant()));
			currentLoan.get().setStatus("Renew time: " + logRenewTime + " at: " + now);
			currentLoan.get().setLogRenew(logRenewTime += 1);
			loanRepository.save(currentLoan.get());

			return LoanMapper.getInstance().toResponse(currentLoan.get());

		} else {
			throw new NotFoundException("Không tìm thấy " + id);
		}


	}


	//scheduling 12h check time returned
//	@Scheduled(fixedRate = 43200000)
	@Transactional
	public void updateTimeExpired() {

		List<Loan> loans = loanRepository.loans();
//		handle time expired
		loans.forEach(loan -> {
			if (loan.getDateReturned() != null && loan.getStatus().equals("has expired.")) {
				if (now.isAfter(loan.getDueDate().toLocalDateTime())) {
					User user = userRepository.getReferenceById(loan.getUser().getId());
					log.info("User Id: " + loan.getUser().getId() + " has expired");
					loan.setStatus("has expired.");
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
