package com.demo.expensetrackerapi.controller;

import com.demo.expensetrackerapi.entity.Expense;
import com.demo.expensetrackerapi.service.ExpenseService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/getexpenses")
    public List<Expense> getAllExpense(Pageable pageable){

        return expenseService.getAllExpenses(pageable).toList();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @GetMapping("/test")
    public Expense getName(@RequestParam String name){

        return expenseService.getName(name);

    }

    @GetMapping("/exporttoexcel")
    public ResponseEntity<byte[]> exportToExcel(Pageable pageable){

        try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){

            List<Expense> expenses = expenseService.getAllExpenses(pageable).stream().toList();

            Sheet sheet = workbook.createSheet("Trasactions");

            String[] headers = new String[]{"Id", "Name", "Description", "Amount", "Date", "Categoty"};

            int n = headers.length;
            Row headerRow = sheet.createRow(0);

            for(int i = 0; i<n; i++){
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            int index = 1;

            for(Expense expense: expenses){
                Row currRow = sheet.createRow(index++);

                currRow.createCell(0).setCellValue(expense.getId());
                currRow.createCell(1).setCellValue(expense.getName());
                currRow.createCell(2).setCellValue(expense.getDescription());
                currRow.createCell(3).setCellValue(expense.getAmount().doubleValue());
                currRow.createCell(4).setCellValue(expense.getDate());
                currRow.createCell(5).setCellValue(expense.getCategory());
            }

            workbook.write(out);

            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.add("Content-Disposition", "attachment; filename=transactions.xlsx");
            return new ResponseEntity<>(out.toByteArray(), responseHeader, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
