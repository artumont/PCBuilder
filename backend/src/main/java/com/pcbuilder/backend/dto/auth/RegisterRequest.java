package com.pcbuilder.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username must be alphanumeric")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password must be alphanumeric")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    String password,

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    String phoneNumber
) {}
