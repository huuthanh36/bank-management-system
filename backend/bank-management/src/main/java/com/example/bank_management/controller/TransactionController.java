package com.example.bank_management.controller;

import com.example.bank_management.dto.request.DepositRequest;
import com.example.bank_management.dto.request.TransferRequest;
import com.example.bank_management.dto.request.WithdrawRequest;
import com.example.bank_management.dto.response.ApiResponse;
import com.example.bank_management.dto.response.TransactionResponse;
import com.example.bank_management.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/accounts/{accountNumber}/deposit")
    public ResponseEntity<ApiResponse<TransactionResponse>> deposit(
            @PathVariable
            String accountNumber,
            @Valid
            @RequestBody
            DepositRequest request
    ){
        TransactionResponse response = transactionService.deposit(accountNumber,request);

        return ResponseEntity.ok(
                ApiResponse.<TransactionResponse>builder()
                        .success(true)
                        .message("Deposit successful")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/accounts/{accountNumber}/withdraw")
    public ResponseEntity<ApiResponse<TransactionResponse>> withdraw(
            @PathVariable String accountNumber,
            @Valid
            @RequestBody
            WithdrawRequest request

    ){
        TransactionResponse response = transactionService.withdraw(accountNumber,request);

        return ResponseEntity.ok(
                ApiResponse.<TransactionResponse>builder()
                        .success(true)
                        .message("Withdraw successful")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/transactions/transfer")
    public ResponseEntity<ApiResponse<TransactionResponse>> transfer(
            @Valid @RequestBody TransferRequest request
    ){

        TransactionResponse response = transactionService.transfer(request);


        return ResponseEntity.ok(
                ApiResponse.<TransactionResponse>builder()
                        .success(true)
                        .message("Transfer successful")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/transactions/accounts/{accountNumber}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionHistory(@PathVariable String accountNumber){
        List<TransactionResponse> responses = transactionService.getTransactionHistory(accountNumber);

        return ResponseEntity.ok(
                ApiResponse.<List<TransactionResponse>>builder()
                        .success(true)
                        .message("Transaction history retrieved successful")
                        .data(responses)
                        .build()
        );
    }

}
