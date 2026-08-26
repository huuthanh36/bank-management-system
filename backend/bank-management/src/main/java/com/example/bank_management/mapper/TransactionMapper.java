package com.example.bank_management.mapper;

import com.example.bank_management.dto.response.TransactionResponse;
import com.example.bank_management.entity.Transaction;

public class TransactionMapper {

    private TransactionMapper() {
    }

    public static TransactionResponse toResponse(
            Transaction transaction
    ) {

        if (transaction == null) {
            return null;
        }

        return TransactionResponse.builder()
                .id(transaction.getId())
                .transactionCode(
                        transaction.getTransactionCode()
                )
                .fromAccount(
                        transaction.getFromAccount() != null
                                ? transaction.getFromAccount()
                                .getAccountNumber()
                                : null
                )
                .toAccount(
                        transaction.getToAccount() != null
                                ? transaction.getToAccount()
                                .getAccountNumber()
                                : null
                )
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .status(transaction.getStatus())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
