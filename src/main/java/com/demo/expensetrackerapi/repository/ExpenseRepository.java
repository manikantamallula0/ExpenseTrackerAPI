package com.demo.expensetrackerapi.repository;

import com.demo.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

   Expense findByName(String name);

   List<Expense> findByAmount(BigDecimal amount);

   List<Expense> findByNameContaining(String name);


   //SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDAte';
   List<Expense> findByDateBetween(Date startDate, Date endDate, Pageable pageable);





}
