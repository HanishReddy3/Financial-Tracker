package com.Project.finance.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {

    private Long id;
    private String title;
    private Integer amount;
    private LocalDate date;
    private String category;
    private String description;

}
