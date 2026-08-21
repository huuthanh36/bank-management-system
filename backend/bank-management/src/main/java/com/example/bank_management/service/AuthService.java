package com.example.bank_management.service;

import com.example.bank_management.dto.request.LoginRequest;
import com.example.bank_management.dto.request.RegisterRequest;
import com.example.bank_management.dto.response.LoginResponse;
import com.example.bank_management.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
