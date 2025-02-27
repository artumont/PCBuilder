package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.GpuService;

@RestController
@RequestMapping("/components/gpu")
public class GpuController {

    private final GpuService gpuService;

    public GpuController(GpuService gpuService) {
        this.gpuService = gpuService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getGpu(
        @RequestParam(required = true) 
        Integer id 

    ) {
        return gpuService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchGpu(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String chipset,

        // @note: Both 'minVram' and 'maxVram' are part of a range.
        @RequestParam(required = false)
        Integer minVram,

        @RequestParam(required = false)
        Integer maxVram,

        // @note: Both 'minWattage' and 'maxWattage' are part of a range.
        @RequestParam(required = false)
        Integer minWattage,

        @RequestParam(required = false)
        Integer maxWattage,

        // @note: Both 'minPrice' and 'maxPrice' are part of a range.
        @RequestParam(required = false)
        Float minPrice,

        @RequestParam(required = false)
        Float maxPrice,

        // @note: Both 'offset' and 'limit' are part of an operation.
        @RequestParam(required = false)
        Integer offset,

        @RequestParam(required = false)
        Integer limit
    ) {
        if (name != null) {
            return gpuService.searchByName(name);
        } else if (chipset != null) {
            return gpuService.searchByChipset(chipset);
        } else if (minVram != null && maxVram != null) {
            return gpuService.searchByVram(minVram, maxVram);
        } else if (minWattage != null && maxWattage != null) {
            return gpuService.searchByWattage(minWattage, maxWattage);
        } else if (minPrice != null && maxPrice != null) {
            return gpuService.searchByPrice(minPrice, maxPrice);
        } else if (offset != null && limit != null) {
            return gpuService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
    
}