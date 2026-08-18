package com.example.bank_management.mapper;

import com.example.bank_management.dto.response.AccountResponse;
import com.example.bank_management.entity.Account;

public class AccountMapper {

    private AccountMapper(){

    }

    public static AccountResponse toResponse(Account account){
        if(account == null){
            return null;
        }

        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .accountType(account.getAccountType())
                .status(account.getStatus())
                .customerId(
                        account.getCustomer() != null ? account.getCustomer().getId() : null
                )
                .customerName(
                        account.getCustomer() != null ? account.getCustomer().getFullName() : null
                )
                .createdAt(account.getCreatedAt())
                .build();
    }
}
