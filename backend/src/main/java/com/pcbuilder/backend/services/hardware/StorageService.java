package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.utils.Logger;

@Service
public class StorageService {
    private final Logger logger;
    private final Connection connection;

    public StorageService(Logger logger, Connection connection) {
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

    public ResponseEntity<MultiHardwareResponse> searchByStorageFormat(String storageFormat, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByStorageFormat'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByStorageProtocol(String storageProtocol, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByStorageProtocol'");
    }

    public ResponseEntity<MultiHardwareResponse> searchBySize(Integer minSize, Integer maxSize, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchBySize'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(Float minPrice, Float maxPrice, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByPrice'");
    }
}
