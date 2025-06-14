package com.Project.finance.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseDTO {

        private Long id;
        private String title;
        private String description;
        private String category;
        private LocalDate date;
        private Integer amount;

}
