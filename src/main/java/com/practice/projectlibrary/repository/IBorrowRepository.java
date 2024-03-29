package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBorrowRepository extends JpaRepository<Loan, Long> {

	//list loan
	@Query(value = "SELECT * FROM loans WHERE active=true", nativeQuery = true)
	List<Loan> loans();

	List<Loan> getAllByActiveIsTrue();

	@Query(value = "SELECT * FROM loans WHERE active=true and id=:id", nativeQuery = true)
	Optional<Loan> selectLoanById(Long id);

//	@Query( "select distinct l from loans as l inner join users as u on l.user_id=u.id")
//	List<Loan> getLoans();

}
