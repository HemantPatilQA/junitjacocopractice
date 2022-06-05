package com.selflerning.junitjacocopractice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selflerning.junitjacocopractice.entities.Expense;
import com.selflerning.junitjacocopractice.repositories.ExpenseRepository;
import com.selflerning.junitjacocopractice.services.ExpenseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ExpenseControllerTest {

    @Autowired
    private ExpenseService expenseService;

    @MockBean
    private ExpenseRepository expenseRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void addExpenseTest() throws Exception {
        Expense exp = new Expense(1L, "Bird Vally", "2022-06-01", 3106.55);

        String expRequest = objectMapper.writeValueAsString(exp);

        when(expenseRepository.save(exp)).thenReturn(exp);

        MvcResult result = mockMvc.perform(post("/expenses/add").content(expRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        Expense expActual = objectMapper.readValue(result.getResponse().getContentAsString(), Expense.class);

        Assertions.assertEquals(exp, expActual);
    }

    @Test
    void getAllExpenseTest() throws Exception {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(new Expense(1L, "Bird Vally", "2022-06-01", 3106.55));
        expenseList.add(new Expense(2L, "Fruits", "2022-06-01", 310.55));
        expenseList.add(new Expense(3L, "Bird Vally", "2022-05-31", 220.75));

        when(expenseRepository.findAll()).thenReturn(expenseList);

        MvcResult result = mockMvc.perform(get("/expenses/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        //List<Expense> expenseListActual = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        Expense[] expenses = objectMapper.readValue(result.getResponse().getContentAsString(), Expense[].class);
        List<Expense> expenseListActual = new ArrayList(Arrays.asList(expenses));

        Assertions.assertEquals(expenseList, expenseListActual);
    }

    @Test
    void getExpensesbyDateTest() throws Exception {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(new Expense(1L, "Bird Vally", "2022-06-01", 3106.55));
        expenseList.add(new Expense(2L, "Fruits", "2022-06-01", 310.55));

        when(expenseRepository.findByDate("2022-06-01")).thenReturn(expenseList);

        MvcResult result = mockMvc.perform(get("/expenses/2022-06-01").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        Expense[] expenses = objectMapper.readValue(result.getResponse().getContentAsString(), Expense[].class);
        List<Expense> expenseListActual = new ArrayList(Arrays.asList(expenses));

        Assertions.assertEquals(expenseList, expenseListActual);

    }
}