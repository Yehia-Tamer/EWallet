package com.example.EWallet.DTO;

import com.example.EWallet.entities.Transaction;
import com.example.EWallet.entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WalletDTO {

    @Min(0)
    private float balance;

    @NotNull
    private UUID walletID;

    @NotBlank
    private String currency;


    private UserResponseDTO user;

    private List<TransactionsDTO> recipientTransactions;

    private List<TransactionsDTO> senderTransactions;

    public WalletDTO(UUID walletID,String currency,float balance) {
        this.walletID=walletID;
        this.currency=currency;
        this.balance=balance;
    }
}
