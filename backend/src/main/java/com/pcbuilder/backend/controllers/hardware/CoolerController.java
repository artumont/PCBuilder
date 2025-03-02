package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.CoolerService;

@RestController
@RequestMapping("/hardware/cooler")
public class CoolerController {

    private final CoolerService coolerService;

    public CoolerController(CoolerService coolerService) {
        this.coolerService = coolerService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getCooler(
        @RequestParam(required = true) 
        Integer id 
    ) {
        return coolerService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchCooler(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String socket,

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
            return coolerService.searchByName(name, limit);
        } else if (socket != null) {
            return coolerService.searchBySocket(socket, limit);
        } else if (minPrice != null && maxPrice != null) {
            return coolerService.searchByPrice(minPrice, maxPrice, limit);
        } else if (offset != null && limit != null) {
            return coolerService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}
