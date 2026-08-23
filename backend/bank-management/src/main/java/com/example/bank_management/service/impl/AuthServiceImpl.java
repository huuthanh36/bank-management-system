package com.example.bank_management.service.impl;

import com.example.bank_management.dto.request.LoginRequest;
import com.example.bank_management.dto.request.RegisterRequest;
import com.example.bank_management.dto.response.LoginResponse;
import com.example.bank_management.dto.response.UserResponse;
import com.example.bank_management.entity.Customer;
import com.example.bank_management.entity.User;
import com.example.bank_management.entity.enums.UserStatus;
import com.example.bank_management.exception.DuplicateResourceException;
import com.example.bank_management.mapper.UserMapper;
import com.example.bank_management.repository.CustomerRepository;
import com.example.bank_management.repository.UserRepository;
import com.example.bank_management.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request) {

        // 1. Check username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException(
                    "Username already exists"
            );
        }

        // 2. Check email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists"
            );
        }

        // 3. Create User
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        // 4. Create Customer
        Customer customer = Customer.builder()
                .user(savedUser)
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .identityNumber(request.getIdentityNumber())
                .dateOfBirth(request.getDateOfBirth())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);

        // 5. Return response
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request){
        throw new UnsupportedOperationException("Login will be implemented in JWT phase");
    }

}
