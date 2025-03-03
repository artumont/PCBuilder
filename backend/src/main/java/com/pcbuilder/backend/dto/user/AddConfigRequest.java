package com.pcbuilder.backend.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AddConfigRequest(
    @NotBlank(message = "Config cannot be null")
    String config,

    @NotBlank(message = "Auth token cannot be empty")
    String authToken
) {} 
