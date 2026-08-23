package com.example.bank_management.controller;

import com.example.bank_management.dto.response.ApiResponse;
import com.example.bank_management.dto.response.CustomerResponse;
import com.example.bank_management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getProfile(
            @PathVariable
            Long userId
    ){
        CustomerResponse response = customerService.getProfile(userId);

        return ResponseEntity.ok(
                ApiResponse.<CustomerResponse>builder()
                        .success(true)
                        .message("Customer profile retrieved successfully")
                        .data(response)
                        .build()
        );
    }
}
