package com.example.bank_management.service;

import com.example.bank_management.dto.request.CreateAccountRequest;
import com.example.bank_management.dto.response.AccountResponse;
import com.example.bank_management.entity.Account;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(
            Long customerId,
            CreateAccountRequest request
    );

    AccountResponse getAccount(
            String accountNumber
    );

    List<AccountResponse> getMyAccounts(
            Long customerId
    );
}
