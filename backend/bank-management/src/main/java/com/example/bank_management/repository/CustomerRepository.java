package com.example.bank_management.repository;

import com.example.bank_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserIdAnd(Long userId);
    Optional<Customer> findByIdentityNumber(
            String identityNumber
    );
}
