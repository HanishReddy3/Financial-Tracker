package com.Project.finance.dto;

import com.Project.finance.entity.Expense;
import com.Project.finance.entity.Income;

import lombok.Data;

@Data
public class StatsDTO {

    private Double income;
    private Double expense;
    // private Income latestIncome;
    // private Expense latestExpense;
    private Double balance;

}
