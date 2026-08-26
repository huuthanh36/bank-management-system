package com.example.bank_management.service.impl;

import com.example.bank_management.dto.request.CreateAccountRequest;
import com.example.bank_management.dto.response.AccountResponse;
import com.example.bank_management.entity.Account;
import com.example.bank_management.entity.Customer;
import com.example.bank_management.entity.enums.AccountStatus;
import com.example.bank_management.exception.ResourecNotFoundException;
import com.example.bank_management.mapper.AccountMapper;
import com.example.bank_management.repository.AccountRepository;
import com.example.bank_management.repository.CustomerRepository;
import com.example.bank_management.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountResponse createAccount(
            Long customerId,
            CreateAccountRequest request
    ) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourecNotFoundException("Customer not found")
                );

        String accountNumber = generateAccountNumber();

        LocalDateTime now = LocalDateTime.now();

        Account account = Account.builder()
                .customer(customer)
                .accountNumber(accountNumber)
                .balance(BigDecimal.ZERO)
                .accountType(request.getAccountType())
                .status(AccountStatus.ACTIVE)
                .createdAt(now)
                .updatedAt(now)
                .build();

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.toResponse(savedAccount);
    }
    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccount(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourecNotFoundException("Account not found"));

        return AccountMapper.toResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getMyAccounts(Long customerId) {
        return accountRepository
                .findByCustomerId(customerId)
                .stream()
                .map(AccountMapper::toResponse)
                .toList();
    }

    private String generateAccountNumber(){
        String accountNumber;

        do{
            accountNumber = String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L));
        }while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

}
