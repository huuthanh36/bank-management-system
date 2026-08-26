package com.example.bank_management.mapper;

import com.example.bank_management.dto.response.UserResponse;
import com.example.bank_management.entity.User;

public class UserMapper {

    private UserMapper(){

    }

    public static UserResponse toResponse(User user){
        if(user == null){
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }
}
