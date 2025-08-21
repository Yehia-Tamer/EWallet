package com.example.EWallet.controllers;

import java.lang.*;
import java.util.UUID;

import com.example.EWallet.DTO.WalletDTO;
import com.example.EWallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    public final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService=walletService;
    }

    @PostMapping("/create")
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO walletDTO) {
        return ResponseEntity.ok(walletService.createWallet(walletDTO));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Float> getBalance(@PathVariable UUID id) {
        return ResponseEntity.ok(walletService.getBalanceById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> getWallet(@PathVariable UUID id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }
}
