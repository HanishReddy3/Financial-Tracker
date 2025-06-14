package com.Project.finance.entity;

import java.time.LocalDate;

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
public class Expense {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;
        private String description;
        private String category;
        private LocalDate date;
        private Integer amount;
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        @JsonIgnore
        private User user;

}
