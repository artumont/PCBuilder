package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.services.hardware.RamService;

@RestController
@RequestMapping("/components/motherboard")
public class RamController {

    private final RamService ramService;

    public RamController(RamService ramService) {
        this.ramService = ramService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getGpu(
        @RequestParam(required = true) 
        Integer id

    ) {
        return ramService.fetchById(id);
    }
}