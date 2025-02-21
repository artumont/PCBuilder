package com.pcbuilder.backend.models.auth;

public record AuthResponse(
    String message,
    String authToken,
    String refreshToken
) {}