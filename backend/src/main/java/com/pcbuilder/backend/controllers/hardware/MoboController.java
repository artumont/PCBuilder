package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.services.hardware.MoboService;

@RestController
@RequestMapping("/components/motherboard")
public class MoboController {

    private final MoboService moboService;

    public MoboController(MoboService moboService) {
        this.moboService = moboService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getGpu(
        @RequestParam(required = false) 
        Integer id, 

        @RequestParam(required = false)
        String socket

        // @todo: Add the other important parameters.
    ) {
        if (id != null) {
            return moboService.fetchById(id);
        } else if (socket != null) {
            return moboService.fetchBySocket(socket);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<HardwareResponse> getMethodName(@RequestParam String param) {
        return null;
    }
}