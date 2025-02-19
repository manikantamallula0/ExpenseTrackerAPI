












package com.demo.expensetrackerapi.service;

import com.demo.expensetrackerapi.entity.Expense;
import com.demo.expensetrackerapi.exception.ResourceNotFoundException;
import com.demo.expensetrackerapi.repository.ExpenseRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;


@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepo;

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepo.findAll(pageable);
    }

    public Expense getName(String name){
        Expense expense = expenseRepo.findByName(name);
        if(expense == null){
            throw new ResourceNotFoundException("Expense not found for the name: " + name);
        }

        return expense;
    }

    public String deleteExpense(Long id){
        Optional<Expense> expense = expenseRepo.findById(id);

        if(expense.isEmpty()){
            throw new ResourceNotFoundException("Expense not found for the id" + id);
        }
        expenseRepo.deleteById(id);
        return "Item got delted sucessfully";
    }


    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = expenseRepo.findById(id).orElse(null);

        if(existingExpense == null)
        {
            return null;
        }

        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        return expenseRepo.save(existingExpense);


    }


    public Expense addExpense(Expense expense) {
        if (expenseRepo.findById(expense.getId()).orElse(null) != null){
            return null;
        }

        return expenseRepo.save(expense);
    }

    public void readExcel()  {



        try(FileInputStream file = new FileInputStream(new File("C:\\Users\\manik\\Downloads\\New Microsoft Excel Worksheet.xlsx"));
        Workbook workbook = new XSSFWorkbook(file)
        ){

            Sheet sheet = workbook.getSheet("Sheet1");
            for (Row row : sheet) { // Iterate through rows
                for (Cell cell : row) { // Iterate through cells
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.print("UNKNOWN\t");
                            break;
                    }
                }
                System.out.println(); // New line after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
