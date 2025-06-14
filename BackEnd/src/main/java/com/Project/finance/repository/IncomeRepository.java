package com.Project.finance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project.finance.dto.IncomeDTO;
import com.Project.finance.entity.Expense;
import com.Project.finance.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT SUM(i.amount) FROM Income i")
    Double sumAllAmounts();

    Optional<Income> findFirstByOrderByDateDesc();

    List<Income> findByUserEmail(String email);

    List<IncomeDTO> findByUserEmailOrderByDateDesc(String email);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user.email = :email")
    Double sumAllAmountsByUserEmail(@Param("email") String email);

}
