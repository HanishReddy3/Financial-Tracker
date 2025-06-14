package com.Project.finance.entity;

import java.time.LocalDate;

import com.Project.finance.dto.IncomeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Income {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;
        private Integer amount;
        private LocalDate date;
        private String category;
        private String description;
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        @JsonIgnore
        private User user;

        public IncomeDTO getIncomeDto() {
                IncomeDTO incomeDTO = new IncomeDTO();

                incomeDTO.setId(id);
                incomeDTO.setTitle(title);
                incomeDTO.setAmount(amount);
                incomeDTO.setDate(date);
                incomeDTO.setCategory(category);
                incomeDTO.setDescription(description);

                return incomeDTO;
        }
}
