package com.Project.finance.services.stats;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Project.finance.dto.StatsDTO;
import com.Project.finance.entity.Expense;
import com.Project.finance.entity.Income;
import com.Project.finance.repository.ExpenseRepository;
import com.Project.finance.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImplementation implements StatsService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public StatsDTO geStats(String email) {
        Double totalIncome = incomeRepository.sumAllAmountsByUserEmail(email);
        Double totalExpense = expenseRepository.sumAllAmountsByUserEmail(email);

        // Optional<Income> optionalIncome =
        // incomeRepository.findFirstByOrderByDateDesc();
        // Optional<Expense> optionalExpense =
        // expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        // if (optionalIncome.isPresent()) {
        // statsDTO.setLatestIncome(optionalIncome.get());
        // }
        // if (optionalExpense.isPresent()) {
        // statsDTO.setLatestExpense(optionalExpense.get());
        // }

        statsDTO.setBalance(totalIncome - totalExpense);
        return statsDTO;
    }

}
