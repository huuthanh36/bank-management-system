package com.example.bank_management.dto.request;

import com.example.bank_management.entity.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    @NotBlank(message = "Account type is required")
    private AccountType accountType;
}
