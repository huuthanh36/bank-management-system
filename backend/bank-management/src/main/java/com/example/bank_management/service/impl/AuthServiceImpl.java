package com.example.bank_management.service.impl;

import com.example.bank_management.dto.request.LoginRequest;
import com.example.bank_management.dto.request.RegisterRequest;
import com.example.bank_management.dto.response.LoginResponse;
import com.example.bank_management.dto.response.UserResponse;
import com.example.bank_management.entity.User;
import com.example.bank_management.entity.enums.UserStatus;
import com.example.bank_management.mapper.UserMapper;
import com.example.bank_management.repository.UserRepository;
import com.example.bank_management.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request){
        if(userRepository.existsByUsername(
                request.getUsername()
        )) {
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(
                request.getUsername()
        )){
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .email(request.getEmail())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request){
        throw new UnsupportedOperationException("Login will be implemented in JWT phase");
    }

}
