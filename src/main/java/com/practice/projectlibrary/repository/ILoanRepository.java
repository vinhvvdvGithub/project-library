package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {

    //list loan
    @Query(value = "SELECT * FROM loans WHERE status=true",nativeQuery = true)
    List<Loan> loans();




}
