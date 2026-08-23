package com.example.bank_management.dto.request;

import com.example.bank_management.entity.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    @NotNull(message = "Account type is required")
    private AccountType accountType;
}
