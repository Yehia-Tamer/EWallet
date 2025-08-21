package com.example.EWallet.repository;

import com.example.EWallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllBySenderWallet_User_Id(UUID userId);
    List<Transaction> findAllByRecepientWallet_User_Id(UUID userId);
}
