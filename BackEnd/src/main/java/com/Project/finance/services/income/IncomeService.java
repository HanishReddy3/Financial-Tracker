package com.Project.finance.services.income;

import java.util.List;

import com.Project.finance.dto.IncomeDTO;
import com.Project.finance.entity.Income;
import com.Project.finance.entity.User;

public interface IncomeService {

    Income postIncome(IncomeDTO incomeDTO, String email);

    List<IncomeDTO> getAllIncomes();

    Income updateIncome(Long id, IncomeDTO incomeDTO, User user);

    IncomeDTO getIncomeById(Long id);

    void deleteIncome(Long id);

    List<IncomeDTO> getIncomesByEmail(String email);
}
