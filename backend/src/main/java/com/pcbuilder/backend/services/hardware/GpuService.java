package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.utils.Logger;

@Service
public class GpuService {
    private final Logger logger;
    private final Connection connection;

    public GpuService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'fetchById'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByRange'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByName'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByChipset(String chipset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByChipset'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByVram(Integer minVram, Integer maxVram, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByVram'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByWattage(Integer minWattage, Integer maxWattage, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByWattage'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(Float minPrice, Float maxPrice, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByPrice'");
    }
}