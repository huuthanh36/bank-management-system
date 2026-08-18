package com.example.bank_management.dto.response;

import com.example.bank_management.entity.enums.TransactionStatus;
import com.example.bank_management.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private Long id;
    private String transactionCode;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private TransactionType transactionType;
    private TransactionStatus status;
    private String description;
    private LocalDateTime createdAt;
}
