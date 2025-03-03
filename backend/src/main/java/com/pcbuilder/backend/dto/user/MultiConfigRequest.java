package com.pcbuilder.backend.dto.user;

import jakarta.validation.constraints.NotBlank;

public record MultiConfigRequest(
    @NotBlank(message = "Config cannot be null")
    String limit,

    @NotBlank(message = "Auth token cannot be empty")
    String authToken
) {} 
