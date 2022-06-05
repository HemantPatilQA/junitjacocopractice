package com.selflerning.junitjacocopractice.controllers;

import com.selflerning.junitjacocopractice.entities.Expense;
import com.selflerning.junitjacocopractice.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense){
        return expenseService.addExpense(expense);
    }

    @GetMapping("/all")
    public List<Expense> getAllExpense(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{date}")
    public List<Expense> getExpensesbyDate(@PathVariable String date){
        return expenseService.getExpenseByDate(date);
    }
}
