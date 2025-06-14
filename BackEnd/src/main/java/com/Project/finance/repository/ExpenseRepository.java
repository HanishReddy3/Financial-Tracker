package com.Project.finance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project.finance.dto.ExpenseDTO;
import com.Project.finance.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllAmounts();

    Optional<Expense> findFirstByOrderByDateDesc();

    List<Expense> findByUserEmail(String email);

    List<ExpenseDTO> findByUserEmailOrderByDateDesc(String email);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.email = :email")
    Double sumAllAmountsByUserEmail(@Param("email") String email);
}
