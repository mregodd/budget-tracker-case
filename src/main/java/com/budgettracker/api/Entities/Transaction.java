package com.budgettracker.api.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String type;

    @Getter
    @Column(nullable = false)
    private double amount;

    @Getter
    @Column(nullable = false)
    private String description;

    @Getter
    @Column(nullable = false)
    private LocalDate date;

}
