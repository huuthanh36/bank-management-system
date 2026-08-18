package com.example.bank_management.dto.response;

import com.example.bank_management.entity.enums.AccountStatus;
import com.example.bank_management.entity.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private AccountStatus status;
    private Long customerId;
    private String customerName;
    private LocalDateTime createdAt;
}
