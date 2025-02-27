package com.pcbuilder.backend.dto.hardware;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.pcbuilder.backend.models.hardware.Hardware;

public record MultiHardwareResponse(
    @NotBlank(message = "Status is required")
    String status,

    @NotBlank(message = "Message is required")
    String message,

    @NotNull(message = "Hardware type is required")
    String hardwareType,

    @NotBlank(message = "Hardware list is required")
    List<Hardware> hardwareList
) {
    public void addHardware(Hardware hardware) {
        hardwareList.add(hardware);
    }
}
