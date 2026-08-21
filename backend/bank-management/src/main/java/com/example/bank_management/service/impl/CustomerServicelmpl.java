package com.example.bank_management.service.impl;


import com.example.bank_management.dto.response.CustomerResponse;
import com.example.bank_management.entity.Customer;
import com.example.bank_management.mapper.CustomerMapper;
import com.example.bank_management.repository.CustomerRepository;
import com.example.bank_management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServicelmpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getProfile(Long userId ){
        Customer customer = customerRepository .findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer no found"));
        return CustomerMapper.toResponse(customer);
    }

}
