package com.budgettracker.api.Controllers;
import com.budgettracker.api.Entities.Transaction;
import com.budgettracker.api.DTOs.TransactionDTO;
import com.budgettracker.api.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        // Kullanıcı doğrulama ve yetkilendirme işlemleri burada yapılmalıdır.

        Transaction savedTransaction = transactionService.addTransaction(transactionDTO);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> editTransaction(@PathVariable Long userId, @RequestBody TransactionDTO updatedTransactionDTO) {
        // Kullanıcı doğrulama ve yetkilendirme işlemleri burada yapılmalıdır.

        Transaction savedTransaction = transactionService.editTransaction(userId, updatedTransactionDTO);
        return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {
        // Kullanıcı doğrulama ve yetkilendirme işlemleri burada yapılmalıdır.

        List<Transaction> userTransactions = transactionService.getUserTransactions(userId);
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<Double> getUserBudgetSummary(@PathVariable Long userId) {
        // Kullanıcı doğrulama ve yetkilendirme işlemleri burada yapılmalıdır.

        Double userBudgetSummary = transactionService.getUserBudgetSummary(userId);
        return new ResponseEntity<>(userBudgetSummary, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/statistics")
    public ResponseEntity<Map<String, Double>> getUserBudgetStatistics(@PathVariable Long userId,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate) {
        // Kullanıcı doğrulama ve yetkilendirme işlemleri burada yapılmalıdır.

        Map<String, Double> userBudgetStatistics = transactionService.getUserBudgetStatistics(userId, selectedDate);
        return new ResponseEntity<>(userBudgetStatistics, HttpStatus.OK);
    }
}