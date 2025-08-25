package com.example.EWallet.controller;

import com.example.EWallet.DTO.TransactionsDTO;
import com.example.EWallet.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    public final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService=transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionsDTO> transfer(@RequestBody @Valid TransactionsDTO dto) {
        return ResponseEntity.ok(transactionService.transfer(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsDTO> getTransaction(@PathVariable @Valid UUID id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionsDTO>> getUserTransactions(@PathVariable @Valid UUID userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUser(userId));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionsDTO> withdraw(@RequestBody @Valid TransactionsDTO dto) {
        return ResponseEntity.ok(transactionService.withdraw(dto));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionsDTO> deposit(@RequestBody @Valid TransactionsDTO dto) {
        return ResponseEntity.ok(transactionService.deposit(dto));
    }
}
