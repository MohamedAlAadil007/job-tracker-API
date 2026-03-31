package com.aadil.jobtracker.Validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Name should be required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email should not be empty")
    private String email;

    @Size(max = 6, message = "Password should be atleast 6 characters")
    @NotBlank(message = "Password is required")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
