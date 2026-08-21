package com.example.bank_management.repository;

import com.example.bank_management.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(
            String accountNumber
    );

    boolean existsByAccountNumber(
            String accountNumber
    );

    List<Account> findByCustomerId(
            Long customerId
    );

    // Tránh 2 tài khoản cùng lúc sửa số dư
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
            """
            SELECT a 
            FROM Account a
            WHERE a.accountNumber = :accountNumber
            """
    )
    Optional<Account> findByAccountNumberForUpdate(
            @Param("accountNumber")
            String accountNumber
    );


}
