package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.RamService;

@RestController
@RequestMapping("/hardware/ram")
public class RamController {

    private final RamService ramService;

    public RamController(RamService ramService) {
        this.ramService = ramService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getRam(
        @RequestParam(required = true) 
        Integer id

    ) {
        return ramService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchRam(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String ramType,

        // @note: Both 'minCapacity' and 'maxCapacity' are part of a range.
        @RequestParam(required = false)
        Integer minCapacity,

        @RequestParam(required = false)
        Integer maxCapacity,

        // @note: Both 'minSpeed' and 'maxSpeed' are part of a range.
        @RequestParam(required = false)
        Integer minSpeed,

        @RequestParam(required = false)
        Integer maxSpeed,

        // @note: Both 'minPrice' and 'maxPrice' are part of a range.
        @RequestParam(required = false)
        Float minPrice,

        @RequestParam(required = false)
        Float maxPrice,

        // @note: Both 'offset' and 'limit' are part of the operations.
        @RequestParam(required = true)
        Integer offset,

        @RequestParam(required = true)
        Integer limit
    ) {
        if (name != null) {
            return ramService.searchByName(name, offset, limit);
        } else if (ramType != null) {
            return ramService.searchByRamType(ramType, offset, limit);
        } else if (minCapacity != null && maxCapacity != null) {
            return ramService.searchByCapacity(minCapacity, maxCapacity, offset, limit);
        } else if (minSpeed != null && maxSpeed != null) {
            return ramService.searchBySpeed(minSpeed, maxSpeed, offset, limit);
        } else if (minPrice != null && maxPrice != null) {
            return ramService.searchByPrice(minPrice, maxPrice, offset, limit);
        } else if (offset != null && limit != null && offset >= 0 && limit > 0) {
            return ramService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}