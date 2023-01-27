package com.example.agenda.login.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginPayload {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

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
