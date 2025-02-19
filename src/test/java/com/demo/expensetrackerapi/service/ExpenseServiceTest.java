package com.demo.expensetrackerapi.service;

import com.demo.expensetrackerapi.entity.Expense;
import com.demo.expensetrackerapi.repository.ExpenseRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    //

    Expense expense = Expense.builder().date(new Date(2000, 12, 13))
            .name("Food").description("Bought dosa").amount(new BigDecimal(100)).build();

    @Test
    public void tesGetName_success(){
        Mockito.when(expenseRepository.findByName("Food")).thenReturn(expense);

        expenseService.getName("Food");

//        assertNotNull(result);
//        assertEquals("Fuel Card", result.getName());
//        assertEquals("Active", result.getStatus());.

        assertNotNull(expense);
        assertEquals("Food", expense.getName());
        assertEquals(new BigDecimal(100), expense.getAmount());

        verify(expenseRepository, times(1)).findByName("Food");

    }

    @Test
    public void testExpoettoExcel(){
        expenseService.readExcel();
    }


}
