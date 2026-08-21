package com.example.bank_management.service;

import com.example.bank_management.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getProfile(Long userId);
}
