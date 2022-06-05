package com.selflerning.junitjacocopractice.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseTest {

    private Expense expense = new Expense();

    @Test
    public void expenseTest(){
        expense.setId(1L);
        expense.setAmount(105.09);
        expense.setDescription("Test Description");
        expense.setDate("2022-06-01");

        assertEquals(1L, expense.getId());
        assertEquals(105.09, expense.getAmount());
        assertEquals("Test Description", expense.getDescription());
        assertEquals("2022-06-01", expense.getDate());


    }

}