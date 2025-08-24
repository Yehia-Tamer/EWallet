package com.example.EWallet.service;

import com.example.EWallet.entities.Wallet;
import com.example.EWallet.DTO.WalletDTO;
import com.example.EWallet.errorhandler.exceptions.WalletNotFoundException;
import com.example.EWallet.repository.UserRepository;
import com.example.EWallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService{
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository=walletRepository;
    }

    public WalletDTO createWallet(WalletDTO dto) {
        Wallet wallet=new Wallet();
        wallet.setCurrency(dto.getCurrency());
        wallet.setBalance(dto.getBalance());

        Wallet saved=walletRepository.save(wallet);

        return new WalletDTO(saved.getWalletID(),saved.getCurrency(),saved.getBalance());
    }
    public WalletDTO getWalletById(UUID id) {
        Wallet wallet=walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
        return new WalletDTO(wallet.getWalletID(), wallet.getCurrency(), wallet.getBalance());
    }

    public float getBalanceById(UUID id) {
        Wallet wallet=walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
        return wallet.getBalance();
    }
}
