package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.MonitorService;

@RestController
@RequestMapping("/hardware/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getMonitor(
        @RequestParam(required = true) 
        Integer id 
    ) {
        return monitorService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchMonitor(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String resolution,

        @RequestParam(required = false)
        Integer refreshRate,

        // @note: Both 'minPrice' and 'maxPrice' are part of a range.
        @RequestParam(required = false)
        Float minPrice,

        @RequestParam(required = false)
        Float maxPrice,

        // @note: Both 'offset' and 'limit' are part of the operations.
        @RequestParam(required = false)
        Integer offset,

        @RequestParam(required = true)
        Integer limit
    ) {
        if (name != null) {
            return monitorService.searchByName(name, limit);
        } else if (resolution != null) {
            return monitorService.searchByResolution(resolution, limit);
        } else if (refreshRate != null) {
            return monitorService.searchByRefreshRate(refreshRate, limit);
        } else if (minPrice != null && maxPrice != null) {
            return monitorService.searchByPrice(minPrice, maxPrice, limit);
        } else if (offset != null && limit != null) {
            return monitorService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}
