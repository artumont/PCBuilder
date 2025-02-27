package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.utils.Logger;

@Service
public class CpuService {
    private final Logger logger;
    private final Connection connection;

    public CpuService(Logger logger, Connection connection) {
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

    public ResponseEntity<MultiHardwareResponse> searchByCores(Integer minCores, Integer maxCores, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByCores'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByClockSpeed(float minClockSpeed, float maxClockSpeed, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByClockSpeed'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByPrice'");
    }
}