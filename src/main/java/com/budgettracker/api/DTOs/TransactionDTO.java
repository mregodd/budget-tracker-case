package com.budgettracker.api.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TransactionDTO {

    private String type; // Gelir , Gider
    private double amount;
    private String description;
    private LocalDate date;

    public TransactionDTO(String type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

}
