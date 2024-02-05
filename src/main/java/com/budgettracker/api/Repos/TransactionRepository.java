package com.budgettracker.api.Repos;

import com.budgettracker.api.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUserIdAndDateGreaterThanEqual(Long userId, LocalDate selectedDate);
}
