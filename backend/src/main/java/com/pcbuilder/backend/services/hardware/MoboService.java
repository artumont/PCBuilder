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
        return null;
    }

    public ResponseEntity<HardwareResponse> fetchBySocket(String socket) {
        return null;
    }

    public ResponseEntity<MultiHardwareResponse> getAll(int offset, int limit) {
        return null;
    }
}
