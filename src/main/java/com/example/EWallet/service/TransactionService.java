package com.example.EWallet.service;

import com.example.EWallet.DTO.TransactionsDTO;
import com.example.EWallet.entities.TransactionType;
import com.example.EWallet.entities.Wallet;
import com.example.EWallet.entities.Transaction;
import com.example.EWallet.errorhandler.exceptions.*;
import com.example.EWallet.repository.TransactionsRepository;
import com.example.EWallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionsRepository transactionsRepository, WalletRepository walletRepository) {
        this.transactionsRepository = transactionsRepository;
        this.walletRepository = walletRepository;
    }

    private TransactionsDTO mapToDTO(Transaction transaction) {
        TransactionsDTO dto = new TransactionsDTO();

        dto.setTransactionsID(transaction.getTransactionID());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setTransactionDateAndTime(transaction.getTransactionDateAndTime());
        dto.setSenderWalletId(transaction.getSenderWallet() != null ? transaction.getSenderWallet().getWalletID() : null);
        dto.setRecipientWalletId(transaction.getRecipientWallet() != null ? transaction.getRecipientWallet().getWalletID() : null);
        return dto;
    }

    @Transactional
    public TransactionsDTO transfer(TransactionsDTO dto) {
        Wallet sender = walletRepository.findById(dto.getSenderWalletId())
                .orElseThrow(() -> new WalletNotFoundException(dto.getSenderWalletId()));
        Wallet recipient = walletRepository.findById(dto.getRecipientWalletId())
                .orElseThrow(() -> new WalletNotFoundException(dto.getRecipientWalletId()));

        if(dto.getAmount()<=0)
            throw new InvalidTransactionAmountException(dto.getAmount());

        if(dto.getAmount()> sender.getBalance())
            throw new InsufficientBalanceException(sender.getWalletID(), sender.getBalance(),dto.getAmount());

        if(sender.getWalletID().equals(recipient.getWalletID()))
            throw new InvalidTransactionException(sender.getWalletID());

        sender.setBalance(sender.getBalance() - dto.getAmount());
        recipient.setBalance(recipient.getBalance() + dto.getAmount());

        walletRepository.save(sender);
        walletRepository.save(recipient);

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setSenderWallet(sender);
        transaction.setRecipientWallet(recipient);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionDateAndTime(LocalDateTime.now());
        transactionsRepository.save(transaction);

        return mapToDTO(transaction);
    }

    @Transactional
    public TransactionsDTO withdraw(TransactionsDTO dto) {
        Wallet wallet = walletRepository.findById(dto.getSenderWalletId())
                .orElseThrow(() -> new WalletNotFoundException(dto.getSenderWalletId()));

        if(dto.getAmount()<=0)
            throw new InvalidTransactionAmountException(dto.getAmount());

        if(dto.getAmount()> wallet.getBalance())
            throw new InsufficientBalanceException(wallet.getWalletID(), wallet.getBalance(), dto.getAmount());

        wallet.setBalance(wallet.getBalance() - dto.getAmount());
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setSenderWallet(wallet);
        transaction.setRecipientWallet(null);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTransactionDateAndTime(LocalDateTime.now());

        transactionsRepository.save(transaction);
        return mapToDTO(transaction);
    }

    @Transactional
    public TransactionsDTO deposit(TransactionsDTO dto) {
        Wallet wallet = walletRepository.findById(dto.getRecipientWalletId())
                .orElseThrow(() -> new WalletNotFoundException(dto.getRecipientWalletId()));

        if(dto.getAmount()<=0)
            throw new InvalidTransactionAmountException(dto.getAmount());

        wallet.setBalance(wallet.getBalance() + dto.getAmount());
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setSenderWallet(null);
        transaction.setRecipientWallet(wallet);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionDateAndTime(LocalDateTime.now());

        transactionsRepository.save(transaction);
        return mapToDTO(transaction);
    }

    public TransactionsDTO getTransactionById(UUID id) {
        Transaction transaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return mapToDTO(transaction);
    }

    public List<TransactionsDTO> getTransactionsByUser(UUID userId) {
        List<Transaction> sent = transactionsRepository.findAllBySenderWallet_User_Id(userId);
        List<Transaction> received = transactionsRepository.findAllByRecipientWallet_User_Id(userId);
        return Stream.concat(sent.stream(),received.stream())
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
