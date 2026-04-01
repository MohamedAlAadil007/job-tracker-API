package com.aadil.jobtracker.Validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;

    public UserResponseDTO(
            @NotBlank(message = "Name should be required") String name
            , @Email(message = "Invalid Email") @NotBlank(message = "Email should not be empty") String email,
              @NotBlank(message = "ID cannot be empty") Long id) {
    }
}