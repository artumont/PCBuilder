package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.models.hardware.Cpu;
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
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.CPUs WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found CPU with id: " + id,
                    "cpu",
                    new Cpu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("socket"),
                        resultSet.getInt("cores"),
                        resultSet.getFloat("clock_speed"),
                        resultSet.getInt("threads"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "CPU with id: " + id + " not found",
                    "cpu",
                    null
                ));
            }
        } catch (Exception e) {
            logger.error("RamService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Hardware.CPUs ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY"
            );
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MultiHardwareResponse multiHardwareResponse = new MultiHardwareResponse(
                    "success",
                    String.format("Found CPUs from %d to %d", offset, offset + limit),
                    "cpu",
                    List.of()
                );
                do {
                    multiHardwareResponse.addHardware(new Cpu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("socket"),
                        resultSet.getInt("cores"),
                        resultSet.getFloat("clock_speed"),
                        resultSet.getInt("threads"),
                        resultSet.getFloat("price")
                    ));
                } while (resultSet.next());
                return ResponseEntity.ok(multiHardwareResponse);
            }
            else {
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "No CPUs found",
                    "cpu",
                    List.of()
                ));
            }
        } catch (Exception e) {
            logger.error("CpuService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
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