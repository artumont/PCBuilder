package com.pcbuilder.backend.dto.hardware;

import com.pcbuilder.backend.models.hardware.Hardware;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HardwareResponse(
    @NotBlank(message = "Status is required")
    String status,

    @NotBlank(message = "Message is required")
    String message,

    @NotNull(message = "Hardware type is required")
    String hardwareType,

    @NotBlank(message = "Hardware is required")
    Hardware hardwareData
) {}
