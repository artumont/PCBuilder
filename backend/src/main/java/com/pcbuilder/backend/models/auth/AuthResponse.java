package com.pcbuilder.backend.models.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthResponse(
    String message,

    @NotBlank(message = "Auth token is required")
    String authToken,
    
    @NotBlank(message = "Refresh token is required")
    String refreshToken
) {}