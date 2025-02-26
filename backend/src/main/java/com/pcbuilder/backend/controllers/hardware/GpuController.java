package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.services.GpuService;

@RestController
@RequestMapping("/components/gpu")
public class GpuController {

    private final GpuService gpuService;

    public GpuController(GpuService gpuService) {
        this.gpuService = gpuService;
    }

    @GetMapping("/get")
    public ResponseEntity<HardwareResponse> getGpu(
        @RequestParam(required = false) 
        Integer id, 
        
        @RequestParam(required = false) 
        String name,

        @RequestParam(required = false)
        String vram

        // @todo: Add the other important parameters.
    ) {
        if (id != null) {
            return gpuService.getGpuById(id);
        } else if (name != null) {
            return gpuService.getGpuByName(name);
        } else {
            return gpuService.getAllGpus();
        }
    }
}