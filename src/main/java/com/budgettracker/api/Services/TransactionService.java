package com.budgettracker.api.Services;

import com.budgettracker.api.DTOs.TransactionDTO;
import com.budgettracker.api.Repos.TransactionRepository;
import com.budgettracker.api.Entities.Transaction;
import com.budgettracker.api.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Kullanıcıya ait bir gelir veya gider eklemek
    public Transaction addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setType(transactionDTO.getType());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(transactionDTO.getDate());

        return transactionRepository.save(transaction);
    }

    // Kullanıcıya ait bir gelir veya gideri güncellemek
    public Transaction editTransaction(Long userId, TransactionDTO updatedTransactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + userId));

        existingTransaction.setType(updatedTransactionDTO.getType());
        existingTransaction.setAmount(updatedTransactionDTO.getAmount());
        existingTransaction.setDescription(updatedTransactionDTO.getDescription());
        existingTransaction.setDate(updatedTransactionDTO.getDate());

        return transactionRepository.save(existingTransaction);
    }

    // Kullanıcıya ait tüm işlemleri getirmek
    public List<Transaction> getUserTransactions(Long userId) {
        transactionRepository.findByUserId(userId);
        return null;
    }

    public Double getUserBudgetSummary(Long userId) {
        List<Transaction> userTransactions = transactionRepository.findByUserId(userId);

        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : userTransactions) {
            if ("income".equalsIgnoreCase(transaction.getType())) {
                totalIncome += transaction.getAmount();
            } else if ("expense".equalsIgnoreCase(transaction.getType())) {
                totalExpense += transaction.getAmount();
            }
        }

        return totalIncome - totalExpense;
    }

    public Map<String, Double> getUserBudgetStatistics(Long userId, LocalDate selectedDate) {
        List<Transaction> userTransactions = transactionRepository.findByUserIdAndDateGreaterThanEqual(userId, selectedDate);

        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : userTransactions) {
            if ("income".equalsIgnoreCase(transaction.getType())) {
                totalIncome += transaction.getAmount();
            } else if ("expense".equalsIgnoreCase(transaction.getType())) {
                totalExpense += transaction.getAmount();
            }
        }

        Map<String, Double> budgetStatistics = new HashMap<>();
        budgetStatistics.put("totalIncome", totalIncome);
        budgetStatistics.put("totalExpense", totalExpense);

        return budgetStatistics;
    }
}
