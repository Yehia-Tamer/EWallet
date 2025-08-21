package com.example.EWallet.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistrationDTO {
    @NotBlank private String username;
    @NotBlank private String firstname;
    @NotBlank private String lastname;
    @NotBlank private String password;
    @NotBlank @Email private String email;
    @NotBlank private String telephoneNumber;
    @NotBlank private String gender;
    @NotNull private LocalDate birthday;
    @NotNull @Min(18) @Max(80) private Integer age;
}
