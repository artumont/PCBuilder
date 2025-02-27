package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.services.hardware.CpuService;

@RestController
@RequestMapping("/components/cpu")
public class CpuController {

    private final CpuService cpuService;

    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getCpu(
        @RequestParam(required = false) 
        Integer id, 

        @RequestParam(required = false)
        String socket

        // @todo: Add the other important parameters.
    ) {
        if (id != null) {
            return cpuService.fetchById(id);
        } else if (socket != null) {
            return cpuService.fetchBySocket(socket);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<HardwareResponse> getMethodName(@RequestParam String param) {
        return null;
    }
    
}