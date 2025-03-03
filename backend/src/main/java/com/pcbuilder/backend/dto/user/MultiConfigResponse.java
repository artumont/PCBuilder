package com.pcbuilder.backend.dto.user;

import java.util.List;

import com.pcbuilder.backend.models.user.ConfigObj;

import jakarta.validation.constraints.NotBlank;

public record MultiConfigResponse(
    @NotBlank(message = "Status is required")
    String status,

    @NotBlank(message = "Message is required")
    String message,

    @NotBlank(message = "Config cannot be null")
    List<ConfigObj> config
) {} 
