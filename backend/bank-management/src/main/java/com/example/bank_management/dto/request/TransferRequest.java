package com.example.bank_management.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequest {

    @NotBlank(message = "From account is required")
    private String fromAccount;

    @NotBlank(message = "To account is required")
    private String toAccount;

    @NotNull(message = "Amount is required")
    @DecimalMin(
            value = "1000.00",
            message = "Minimum transfer amount is 1000"
    )
    private BigDecimal amount;

    @Size(
            max = 255,
            message = "Description cannot exceed 255 characters"
    )
    private String description;
}
