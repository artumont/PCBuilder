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
            logger.info("CpuService.fetchById", String.format("Fetching CPU with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.CPUs WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("CpuService.fetchById", String.format("Found CPU with id: %s", id));
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
                logger.info("CpuService.fetchById", String.format("CPU with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "CPU with id: " + id + " not found",
                    "cpu",
                    null
                ));
            }
        } catch (Exception e) {
            logger.error("RamService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching CPU",
                "cpu",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("CpuService.searchByRange", String.format("Searching for CPUs with offset: %s and limit: %s", offset, limit));
            PreparedStatement countStatement = connection.prepareStatement("SELECT COUNT(*) as total FROM Hardware.CPUs");
            ResultSet countResult = countStatement.executeQuery();
            countResult.next();
            int totalCount = countResult.getInt("total");
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.CPUs ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("CpuService.searchByRange", String.format("Found %d CPUs", totalCount));
                MultiHardwareResponse multiHardwareResponse = new MultiHardwareResponse(
                    "success",
                    String.format("Found %d CPUs", totalCount),
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
            } else {
                logger.info("CpuService.searchByRange", "No CPUs found");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "No CPUs found",
                    "cpu",
                    List.of()
                ));
            }
        } catch (Exception e) {
            logger.error("CpuService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
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