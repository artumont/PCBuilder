package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.CpuService;

@RestController
@RequestMapping("/hardware/cpu")
public class CpuController {

    private final CpuService cpuService;

    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getCpu(
        @RequestParam(required = true) 
        Integer id

    ) {
        return cpuService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchCpu(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String socket,
        
        // @note: Both 'minCores' and 'maxCores' are part of a range.
        @RequestParam(required = false)
        Integer minCores,

        @RequestParam(required = false)
        Integer maxCores,

        // @note: Both 'minClockSpeed' and 'maxClockSpeed' are part of a range.
        @RequestParam(required = false)
        Float minClockSpeed, 

        @RequestParam(required = false)
        Float maxClockSpeed,

        // @note: Both 'minPrice' and 'maxPrice' are part of a range.
        @RequestParam(required = false)
        Float minPrice,
        
        @RequestParam(required = false)
        Float maxPrice,

        // @note: Both 'offset' and 'limit' are part of the operations.
        @RequestParam(required = false)
        Integer offset,

        @RequestParam(required = false)
        Integer limit
    ) {
        if (name != null) {
            return cpuService.searchByName(name, offset, limit);
        } else if (socket != null) {
            return cpuService.searchBySocket(socket, offset, limit);
        } else if (minCores != null && maxCores != null) {
            return cpuService.searchByCores(minCores, maxCores, offset, limit);
        } else if (minClockSpeed != null && maxClockSpeed != null) {
            return cpuService.searchByClockSpeed(minClockSpeed, maxClockSpeed, offset, limit);
        } else if (minPrice != null && maxPrice != null) {
            return cpuService.searchByPrice(minPrice, maxPrice, offset, limit);
        } else if (offset != null && limit != null) {
            return cpuService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}