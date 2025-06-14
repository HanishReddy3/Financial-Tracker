package com.Project.finance.services.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Project.finance.dto.ExpenseDTO;
import com.Project.finance.dto.IncomeDTO;
import com.Project.finance.entity.Expense;
import com.Project.finance.entity.Income;
import com.Project.finance.entity.User;
import com.Project.finance.repository.IncomeRepository;
import com.Project.finance.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImplementation implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public Income postIncome(IncomeDTO incomeDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return saveOrUpdateIncome(new Income(), incomeDTO, user);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO, User user) {
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setUser(user);

        return incomeRepository.save(income);
    }

    public List<IncomeDTO> getAllIncomes() {
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO, User user) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);

        if (optionalIncome.isPresent()) {
            Income income = optionalIncome.get();

            // Optional: check ownership
            if (!income.getUser().getId().equals(user.getId())) {
                throw new SecurityException("You are not authorized to update this income record.");
            }

            return saveOrUpdateIncome(income, incomeDTO, user);
        } else {
            throw new EntityNotFoundException("Income is not present at ID: " + id);
        }
    }

    public IncomeDTO getIncomeById(Long id) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDto();
        } else {
            throw new EntityNotFoundException("Income not present at the Id :" + id);
        }
    }

    public void deleteIncome(Long id) {
        Optional<Income> optionalExpense = incomeRepository.findById(id);
        if (optionalExpense.isPresent()) {
            incomeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense is not present with Id :" + id);
        }
    }

    public List<IncomeDTO> getIncomesByEmail(String email) {
        List<Income> incomes = incomeRepository.findByUserEmail(email);
        return incomes.stream()
                .map(income -> new IncomeDTO(
                        income.getId(),
                        income.getTitle(),
                        income.getAmount(),
                        income.getDate(),
                        income.getCategory(),
                        income.getDescription()))
                .collect(Collectors.toList());
    }

}
