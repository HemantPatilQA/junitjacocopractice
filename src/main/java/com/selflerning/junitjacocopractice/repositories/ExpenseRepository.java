package com.selflerning.junitjacocopractice.repositories;

import com.selflerning.junitjacocopractice.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDate(String date);
}
