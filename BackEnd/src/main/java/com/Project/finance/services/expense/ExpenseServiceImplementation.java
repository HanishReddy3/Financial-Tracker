package com.Project.finance.services.expense;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.finance.dto.ExpenseDTO;
import com.Project.finance.entity.Expense;
import com.Project.finance.entity.User;
import com.Project.finance.repository.ExpenseRepository;
import com.Project.finance.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImplementation implements ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public Expense postExpense(ExpenseDTO expenseDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return saveOrUpdateExpense(new Expense(), expenseDTO, user);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO, User user) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setUser(user); // Link to authenticated user

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new EntityNotFoundException("Expense not present at the Id :" + id);
        }
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO, User user) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();

            // Optional: Ensure this expense belongs to the logged-in user
            if (!expense.getUser().getId().equals(user.getId())) {
                throw new SecurityException("You are not authorized to update this expense");
            }

            return saveOrUpdateExpense(expense, expenseDTO, user);
        } else {
            throw new EntityNotFoundException("Expense is not present at ID: " + id);
        }
    }

    public void deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            expenseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense is not present with Id :" + id);
        }
    }

    public List<ExpenseDTO> getExpensesByEmail(String email) {
        List<Expense> expenses = expenseRepository.findByUserEmail(email);
        return expenses.stream()
                .map(expense -> new ExpenseDTO(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getDescription(),
                        expense.getCategory(),
                        expense.getDate(),
                        expense.getAmount()))
                .collect(Collectors.toList());
    }
}
