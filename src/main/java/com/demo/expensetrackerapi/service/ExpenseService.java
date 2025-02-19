package com.demo.expensetrackerapi.service;

import com.demo.expensetrackerapi.entity.Expense;

import com.demo.expensetrackerapi.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {

   Page<Expense> getAllExpenses(Pageable pageable);

    Expense getName(String name);

    String deleteExpense(Long id);

    //Expense updateExpense(Long id, Expense expense);

   // Expense addExpense(Expense expense);

}
