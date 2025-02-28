package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.utils.Logger;

@Service
public class RamService {
    private final Logger logger;
    private final Connection connection;

    public RamService(Logger logger, Connection connection) {
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

    public ResponseEntity<MultiHardwareResponse> searchByRamType(String ramType, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByRamType'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByCapacity(Integer minCapacity, Integer maxCapacity, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByCapacity'");
    }

    public ResponseEntity<MultiHardwareResponse> searchBySpeed(Integer minSpeed, Integer maxSpeed, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchBySpeed'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(Float minPrice, Float maxPrice, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByPrice'");
    }
}