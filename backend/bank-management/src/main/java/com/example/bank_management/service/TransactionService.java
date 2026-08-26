package com.example.bank_management.service;

import com.example.bank_management.dto.request.DepositRequest;
import com.example.bank_management.dto.request.TransferRequest;
import com.example.bank_management.dto.request.WithdrawRequest;
import com.example.bank_management.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse deposit(
            String accountNumber,
            DepositRequest request
    );

    TransactionResponse withdraw(
            String accountNumber,
            WithdrawRequest request
    );

    TransactionResponse transfer(
            TransferRequest request
    );

    List<TransactionResponse> getTransactionHistory(
            String accountNumber
    );
}
