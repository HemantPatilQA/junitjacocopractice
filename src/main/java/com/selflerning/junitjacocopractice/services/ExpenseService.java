package com.selflerning.junitjacocopractice.services;

import com.selflerning.junitjacocopractice.entities.Expense;
import com.selflerning.junitjacocopractice.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public List<Expense> getExpenseByDate(String date){
        return expenseRepository.findByDate(date);
    }
}
