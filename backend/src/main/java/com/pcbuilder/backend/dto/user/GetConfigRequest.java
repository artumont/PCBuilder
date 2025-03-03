package com.pcbuilder.backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GetConfigRequest(
    @NotNull(message = "Config ID cannot be null")
    Integer id,

    @NotBlank(message = "Auth token cannot be empty")
    String authToken
) {}
