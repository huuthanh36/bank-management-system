package com.example.bank_management.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawRequest {
    @NotNull(message = "Amount is required")
    @DecimalMin(
            value = "1000.00",
            message = "Minimum withdrawal amount is 1000"
    )
    private BigDecimal amount;

    @Size(
            max = 255,
            message = "Description cannot exceed 255 characters"
    )
    private String description;
}
