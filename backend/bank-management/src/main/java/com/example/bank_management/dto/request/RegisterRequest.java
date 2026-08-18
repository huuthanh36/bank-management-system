package com.example.bank_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(
            min = 4,
            max = 50,
            message = "Username must be between 4 and 50 characters"
    )
    private String username;

    @NotBlank(message = "Password is required")
    @Size(
            min = 8,
            max = 100,
            message = "Password must be between 8 and 100 characters"
    )
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Full name id required")
    @Size(
            max = 100,
            message = "Full name cannot exceed 100 characters"
    )
    private String fullName;

    @Size(max = 20)
    private String phone;

    private String address;

    @Size(max = 20)
    private String identityNumber;

    private LocalDate dateOfBirth;
}
