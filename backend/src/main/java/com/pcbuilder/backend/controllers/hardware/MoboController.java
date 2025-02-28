package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.MoboService;

@RestController
@RequestMapping("/hardware/motherboard")
public class MoboController {

    private final MoboService moboService;

    public MoboController(MoboService moboService) {
        this.moboService = moboService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getMobo(
        @RequestParam(required = true) 
        Integer id

    ) {
        return moboService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> seachMobo(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String socket,

        @RequestParam(required = false)
        String ramType,

        @RequestParam(required = false)
        String size,
        
        @RequestParam(required = false)
        Integer chipsetId,

        // @note: Both 'minSataSlots' and 'maxSataSlots' are part of a range.
        @RequestParam(required = false)
        Integer minSataSlots,

        @RequestParam(required = false)
        Integer maxSataSlots,

        // @note: Both 'minM2Slots' and 'maxM2Slots' are part of a range.
        @RequestParam(required = false)
        Integer minM2Slots,

        @RequestParam(required = false)
        Integer maxM2Slots,

        // @note: Both 'minRamSlots' and 'maxRamSlots' are part of a range.
        @RequestParam(required = false)
        Integer minRamSlots,

        @RequestParam(required = false)
        Integer maxRamSlots,

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
            return moboService.searchByName(name, limit);
        } else if (socket != null) {
            return moboService.searchBySocket(socket, limit);
        } else if (ramType != null) {
            return moboService.searchByRamType(ramType, limit);
        } else if (size != null) {
            return moboService.searchBySize(size, limit);
        } else if (chipsetId != null) {
            return moboService.searchByChipset(chipsetId, limit);
        } else if (minSataSlots != null && maxSataSlots != null) {
            return moboService.searchBySataSlots(minSataSlots, maxSataSlots, limit);
        } else if (minM2Slots != null && maxM2Slots != null) {
            return moboService.searchByM2Slots(minM2Slots, maxM2Slots, limit);
        } else if (minRamSlots != null && maxRamSlots != null) {
            return moboService.searchByRamSlots(minRamSlots, maxRamSlots, limit);
        } else if (minPrice != null && maxPrice != null) {
            return moboService.searchByPrice(minPrice, maxPrice, limit);
        } else if (offset != null && limit != null) {
            return moboService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}