package com.Project.finance.services.expense;

import java.util.List;

import com.Project.finance.dto.ExpenseDTO;
import com.Project.finance.entity.Expense;
import com.Project.finance.entity.User;

public interface ExpenseService {

    Expense postExpense(ExpenseDTO expenseDTO, String email);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO, User user);

    void deleteExpense(Long id);

    List<ExpenseDTO> getExpensesByEmail(String email);
}
