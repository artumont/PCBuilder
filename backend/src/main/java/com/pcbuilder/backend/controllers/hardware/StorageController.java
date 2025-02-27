package com.pcbuilder.backend.controllers.hardware;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.services.hardware.StorageService;

@RestController
@RequestMapping("/components/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<HardwareResponse> getStorage(
        @RequestParam(required = true) 
        Integer id

    ) {
        return storageService.fetchById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MultiHardwareResponse> searchRam(
        @RequestParam(required = false)
        String name,

        @RequestParam(required = false)
        String storageFormat,

        @RequestParam(required = false)
        String storageProtocol,

        // @note: Both 'minSize' and 'maxSize' are part of a range.
        @RequestParam(required = false)
        Integer minSize,

        @RequestParam(required = false)
        Integer maxSize,

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
            return storageService.searchByName(name, offset, limit);
        } else if (storageFormat != null) {
            return storageService.searchByStorageFormat(storageFormat, offset, limit);
        } else if (storageProtocol != null) {
            return storageService.searchByStorageProtocol(storageProtocol, offset, limit);
        } else if (minSize != null && maxSize != null) {
            return storageService.searchBySize(minSize, maxSize, offset, limit);
        } else if (minPrice != null && maxPrice != null) {
            return storageService.searchByPrice(minPrice, maxPrice, offset, limit);
        } else if (offset != null && limit != null) {
            return storageService.searchByRange(offset, limit);
        }
        return ResponseEntity.badRequest().build();
    }
}