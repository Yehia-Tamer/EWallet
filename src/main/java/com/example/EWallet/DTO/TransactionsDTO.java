package com.example.EWallet.DTO;

import com.example.EWallet.entities.TransactionType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionsDTO {

    @NotNull
    private UUID transactionsID;

    private LocalDateTime transactionDateAndTime;

    @Positive
    private float amount;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    private UUID senderWalletId;

    @NotNull
    private UUID recipientWalletId;
}
