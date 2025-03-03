package com.pcbuilder.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequest(
    @NotBlank(message = "Refresh token cannot be null")
    String refreshToken
) {}