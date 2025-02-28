package com.pcbuilder.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username must be alphanumeric")
    String username,
    
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password must be alphanumeric")
    String password
) {}
