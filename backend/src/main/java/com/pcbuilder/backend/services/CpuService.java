package com.pcbuilder.backend.services;

import java.sql.Connection;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.utils.Logger;
import com.pcbuilder.backend.dto.hardware.HardwareResponse;

@Service
public class CpuService {

    private final Logger logger;
    private final Connection connection;

    public CpuService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> getCpuById(int id) {
        return null;
    }

    public ResponseEntity<HardwareResponse> getCpuByName(String name) {
        return null;
    }

    public ResponseEntity<HardwareResponse> getCpuBySocket(String socket) {
        return null;
    }

    public ResponseEntity<HardwareResponse> getAllCpus() {
        return null;
    }
}