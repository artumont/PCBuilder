package com.pcbuilder.backend.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthResponse(
    @NotBlank(message = "Status is required")
    String status,

    @NotBlank(message = "Message is required")
    String message,

    @NotBlank(message = "Auth token is required")
    String profileData
) {}