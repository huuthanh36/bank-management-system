package com.example.bank_management.controller;

import com.example.bank_management.dto.request.CreateAccountRequest;
import com.example.bank_management.dto.response.AccountResponse;
import com.example.bank_management.dto.response.ApiResponse;
import com.example.bank_management.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Literal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> createAccount(
            @RequestParam
            Long customerId,
            @Valid
            @RequestBody
            CreateAccountRequest request
    ){
        AccountResponse response = accountService.createAccount(customerId,request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<AccountResponse>builder()
                                .success(true)
                                .message("Account created successful")
                                .data(response)
                                .build()
                );
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccount(
            @PathVariable
            String accountNumber
    ){
        AccountResponse response = accountService.getAccount(accountNumber);

        return ResponseEntity.ok(
                ApiResponse.<AccountResponse>builder()
                        .success(true)
                        .message("Account retrieved successful")
                        .data(response)
                        .build()
        );
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getMyAccounts(
            @PathVariable
            Long customerId
    ){
        List<AccountResponse> responses = accountService.getMyAccounts(customerId);

        return ResponseEntity.ok(
                ApiResponse.<List<AccountResponse>>builder()
                        .success(true)
                        .message("Accounts retrieved successful")
                        .data(responses)
                        .build()
        );
    }
}
