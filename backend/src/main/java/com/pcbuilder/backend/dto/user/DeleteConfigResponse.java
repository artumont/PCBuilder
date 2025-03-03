package com.pcbuilder.backend.dto.user;

import jakarta.validation.constraints.NotBlank;

public record DeleteConfigResponse(
    @NotBlank(message = "Status is required")
    String status,

    @NotBlank(message = "Message is required")
    String message
) {}
