package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.PsuService;

@RestController
@RequestMapping("/hardware/psu")
public class PsuController {

    private final PsuService psuService;

    public PsuController(PsuService psuService) {
        this.psuService = psuService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getPsu(
        @RequestParam(required = true) 
        Integer id
    ) {
        return psuService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchPsu(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String size,
        
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

        // @note: Both 'offset' and 'limit' are part of the operations.
        @RequestParam(required = false)
        Integer offset,

        @RequestParam(required = true)
        Integer limit
    ) {
        if (name != null) {
            return psuService.searchByName(name, limit);
        } else if (size != null) {
            return psuService.searchBySize(size, limit);
        } else if (minWattage != null && maxWattage != null) {
            return psuService.searchByWattage(minWattage, maxWattage, limit);
        } else if (minPrice != null && maxPrice != null) {
            return psuService.searchByPrice(minPrice, maxPrice, limit);
        } else if (offset != null && limit != null) {
            return psuService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}
