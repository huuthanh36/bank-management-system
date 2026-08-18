package com.example.bank_management.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String identityNumber;
    private LocalDate dateOfBirth;
}
