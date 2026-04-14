package com.aadil.jobtracker.Validation;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserRequestDTO {

    private Long id;
    @NotBlank(message = "Name should be required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email should not be empty")
    @Column(unique = true)
    private String email;

    @Size(max = 6, message = "Password should be least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;
}