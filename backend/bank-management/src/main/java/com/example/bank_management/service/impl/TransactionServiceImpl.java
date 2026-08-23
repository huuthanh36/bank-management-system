package com.example.bank_management.service.impl;

import com.example.bank_management.dto.request.DepositRequest;
import com.example.bank_management.dto.request.TransferRequest;
import com.example.bank_management.dto.request.WithdrawRequest;
import com.example.bank_management.dto.response.TransactionResponse;
import com.example.bank_management.entity.Account;
import com.example.bank_management.entity.Transaction;
import com.example.bank_management.entity.enums.AccountStatus;
import com.example.bank_management.entity.enums.TransactionStatus;
import com.example.bank_management.entity.enums.TransactionType;
import com.example.bank_management.exception.BadRequestException;
import com.example.bank_management.exception.InsufficientBalanceException;
import com.example.bank_management.exception.ResourecNotFoundException;
import com.example.bank_management.mapper.TransactionMapper;
import com.example.bank_management.repository.AccountRepository;
import com.example.bank_management.repository.TransactionRepository;
import com.example.bank_management.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionResponse deposit(
            String accountNumber,
            DepositRequest request
    ){
        Account account = accountRepository.findByAccountNumberForUpdate(accountNumber)
                .orElseThrow(() -> new ResourecNotFoundException("Account not found"));

        validateActiveAccount(account);

        BigDecimal amount = request.getAmount();
        account.setBalance(account.getBalance().add(amount));

        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .transactionCode(generateTransactionCode())
                .fromAccount(null)
                .toAccount(account)
                .amount(amount)
                .transactionType(TransactionType.DEPOSIT)
                .status(TransactionStatus.SUCCESS)
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toResponse(
                savedTransaction
        );
    }

    private void validateActiveAccount(Account account){
        if(account.getStatus() != AccountStatus.ACTIVE){
            throw new RuntimeException("Account is not active");
        }
    }

    private String generateTransactionCode(){
        return "TXN - "+ UUID.randomUUID().toString()
                .replace("-","")
                .substring(0,20)
                .toUpperCase();
    }
    @Override
    @Transactional
    public TransactionResponse withdraw(
            String accountNumber,
            WithdrawRequest request
    ){
        Account account = accountRepository.findByAccountNumberForUpdate(accountNumber)
                .orElseThrow(() -> new ResourecNotFoundException("Account not found"));

        validateActiveAccount(account);

        BigDecimal amount = request.getAmount();

        if(account.getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        accountRepository.save(account);

        Transaction transaction = Transaction.builder().transactionCode(generateTransactionCode())
                .fromAccount(account)
                .toAccount(null)
                .amount(amount)
                .transactionType(TransactionType.WITHDRAW)
                .status(TransactionStatus.SUCCESS)
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toResponse(savedTransaction);

    }
    @Override
    @Transactional
    public TransactionResponse transfer(TransferRequest request){
        if(request.getFromAccount().equals(request.getToAccount())){
            throw new BadRequestException("Cannot transfer to the same account");
        }

        Account fromAccount = accountRepository.findByAccountNumberForUpdate(request.getFromAccount())
                .orElseThrow(() -> new ResourecNotFoundException("Sender account not found"));

        Account toAccount = accountRepository.findByAccountNumberForUpdate(request.getToAccount())
                .orElseThrow(() -> new ResourecNotFoundException("Receiver account not found"));

        validateActiveAccount(fromAccount);
        validateActiveAccount(toAccount);

        BigDecimal amount = request.getAmount();

        if(fromAccount.getBalance().compareTo(amount) < 0 ){
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        Transaction transaction = Transaction.builder()
                .transactionCode(generateTransactionCode())
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .transactionType(TransactionType.TRANSFER)
                .status(TransactionStatus.SUCCESS)
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toResponse(savedTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> getTransactionHistory(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourecNotFoundException("Account not found"));

        List<Transaction> outgoing = transactionRepository.findByFromAccountId(account.getId());

        List<Transaction> incoming = transactionRepository.findByToAccountId(account.getId());

        return java.util.stream.Stream
                .concat(
                        outgoing.stream(),
                        incoming.stream()
                )
                .sorted(
                        java.util.Comparator.comparing(Transaction::getCreatedAt).reversed()
                )
                .map(TransactionMapper::toResponse)
                .toList();
    }
}
