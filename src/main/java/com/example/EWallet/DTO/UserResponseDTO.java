package com.example.EWallet.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    @NotNull private UUID id;
    @NotBlank private String username;
    @Email @NotBlank private String email;
    @NotBlank private String firstname;
    @NotBlank private String lastname;
    private WalletDTO wallet;

    public UserResponseDTO(UUID id,String username,String email,String firstname,String lastname) {
        this.id=id;
        this.username=username;
        this.email=email;
        this.firstname=firstname;
        this.lastname=lastname;
    }
}
