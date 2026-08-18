package com.example.bank_management.repository;

import com.example.bank_management.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionCode(
            String transactionCode
    );

    List<Transaction> findByFromAccountId(
            Long accountId
    );

    List<Transaction> findByToAccountId(
            Long accountId
    );

}
