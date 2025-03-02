package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.CaseService;

@RestController
@RequestMapping("/hardware/case")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getCase(
        @RequestParam(required = true) 
        Integer id 
    ) {
        return caseService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchCase(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String size,

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
            return caseService.searchByName(name, limit);
        } else if (size != null) {
            return caseService.searchBySize(size, limit);
        } else if (minPrice != null && maxPrice != null) {
            return caseService.searchByPrice(minPrice, maxPrice, limit);
        } else if (offset != null && limit != null) {
            return caseService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}
