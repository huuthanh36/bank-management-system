package com.example.bank_management.repository;

import com.example.bank_management.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(
            String accountNumber
    );

    boolean existByAccountNumber(
            String accountNumber
    );

    List<Account> findByCustomerId(
            Long customerId
    );


}
