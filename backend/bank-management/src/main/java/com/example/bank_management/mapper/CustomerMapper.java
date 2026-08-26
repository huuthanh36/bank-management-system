package com.example.bank_management.mapper;

import com.example.bank_management.dto.response.CustomerResponse;
import com.example.bank_management.entity.Customer;

public class CustomerMapper {

    private CustomerMapper(){
    }

    public static CustomerResponse toResponse(Customer customer){
        if(customer == null){
            return null;
        }
        return CustomerResponse.builder()
                .id(customer.getId())
                .userId(
                        customer.getUser() != null ? customer.getUser().getId() : null
                )
                .username(
                        customer.getUser() != null ? customer.getUser().getUsername() : null
                )
                .email(
                        customer.getUser() != null ? customer.getUser().getEmail() : null
                )
                .fullName(customer.getFullName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .identityNumber(customer.getIdentityNumber())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }
}
