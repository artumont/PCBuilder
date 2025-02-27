package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.utils.Logger;

@Service
public class MoboService {
    private final Logger logger;
    private final Connection connection;

    public MoboService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'fetchById'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByRange'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByName'");
    }

    public ResponseEntity<MultiHardwareResponse> searchBySocket(String socket, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchBySocket'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByRamType(String ramType, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByRamType'");
    }

    public ResponseEntity<MultiHardwareResponse> searchBySize(String size, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchBySize'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByChipset(Integer chipsetId, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByChipset'");
    }

    public ResponseEntity<MultiHardwareResponse> searchBySataSlots(Integer minSataSlots, Integer maxSataSlots, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchBySataSlots'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByRamSlots(Integer minRamSlots, Integer maxRamSlots, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByRamSlots'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByM2Slots(Integer minM2Slots, Integer maxM2Slots, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByM2Slots'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(Float minPrice, Float maxPrice, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByPrice'");
    }
}
