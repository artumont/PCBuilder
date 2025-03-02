package com.pcbuilder.backend.services.hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.hardware.HardwareResponse;
import com.pcbuilder.backend.dto.hardware.MultiHardwareResponse;
import com.pcbuilder.backend.models.hardware.Monitor;
import com.pcbuilder.backend.models.hardware.Hardware;
import com.pcbuilder.backend.utils.Logger;

@Service
public class MonitorService {
    private final Logger logger;
    private final Connection connection;

    public MonitorService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        try {
            logger.info("MonitorService.fetchById", String.format("Fetching Monitor with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Monitors WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("MonitorService.fetchById", String.format("Found Monitor with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found Monitor with id: " + id,
                    "monitor",
                    new Monitor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("resolution"),
                        resultSet.getInt("refresh_rate"),
                        resultSet.getDouble("size"),
                        resultSet.getString("panel_type"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("MonitorService.fetchById", String.format("Monitor with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "Monitor with id: " + id + " not found",
                    "monitor",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("MonitorService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Monitor",
                "monitor",
                null
            ));
        } catch (Exception e) {
            logger.error("MonitorService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Monitor",
                "monitor",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("MonitorService.searchByRange", String.format("Searching for Monitors with offset: %s and limit: %s", offset, limit));
            List<Monitor> monitors = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Monitors ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        monitors.add(new Monitor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("resolution"),
                            resultSet.getInt("refresh_rate"),
                            resultSet.getDouble("size"),
                            resultSet.getString("panel_type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!monitors.isEmpty()) {
                logger.info("MonitorService.searchByRange", String.format("Successfully fetched %d Monitors", monitors.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Monitors", monitors.size()),
                    "monitor",
                    new ArrayList<Hardware>(monitors)
                ));
            } else {
                logger.info("MonitorService.searchByRange", "Failed to fetch Monitors");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Monitors",
                    "monitor",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MonitorService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MonitorService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("MonitorService.searchByName", String.format("Searching for Monitors with name: %s", name));
            List<Monitor> monitors = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Monitors WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        monitors.add(new Monitor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("resolution"),
                            resultSet.getInt("refresh_rate"),
                            resultSet.getDouble("size"),
                            resultSet.getString("panel_type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!monitors.isEmpty()) {
                logger.info("MonitorService.searchByName", String.format("Successfully fetched %d Monitors", monitors.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Monitors", monitors.size()),
                    "monitor",
                    new ArrayList<Hardware>(monitors)
                ));
            } else {
                logger.info("MonitorService.searchByName", "Failed to fetch Monitors");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Monitors",
                    "monitor",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MonitorService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MonitorService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByResolution(String resolution, Integer limit) {
        try {
            logger.info("MonitorService.searchByResolution", String.format("Searching for Monitors with resolution: %s", resolution));
            List<Monitor> monitors = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Monitors WHERE LOWER(resolution) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(resolution)), resolution"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + resolution + "%"); 
                statement.setString(3, resolution);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        monitors.add(new Monitor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("resolution"),
                            resultSet.getInt("refresh_rate"),
                            resultSet.getDouble("size"),
                            resultSet.getString("panel_type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!monitors.isEmpty()) {
                logger.info("MonitorService.searchByResolution", String.format("Successfully fetched %d Monitors", monitors.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Monitors", monitors.size()),
                    "monitor",
                    new ArrayList<Hardware>(monitors)
                ));
            } else {
                logger.info("MonitorService.searchByResolution", "Failed to fetch Monitors");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Monitors",
                    "monitor",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MonitorService.searchByResolution", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MonitorService.searchByResolution", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRefreshRate(int refreshRate, Integer limit) {
        try {
            logger.info("MonitorService.searchByRefreshRate", String.format("Searching for Monitors with refresh rate: %d", refreshRate));
            List<Monitor> monitors = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Monitors WHERE refresh_rate >= ? ORDER BY refresh_rate"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, refreshRate);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        monitors.add(new Monitor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("resolution"),
                            resultSet.getInt("refresh_rate"),
                            resultSet.getDouble("size"),
                            resultSet.getString("panel_type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!monitors.isEmpty()) {
                logger.info("MonitorService.searchByRefreshRate", String.format("Successfully fetched %d Monitors", monitors.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Monitors", monitors.size()),
                    "monitor",
                    new ArrayList<Hardware>(monitors)
                ));
            } else {
                logger.info("MonitorService.searchByRefreshRate", "Failed to fetch Monitors");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Monitors",
                    "monitor",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MonitorService.searchByRefreshRate", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MonitorService.searchByRefreshRate", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("MonitorService.searchByPrice", String.format("Searching for Monitors with price between %f and %f", minPrice, maxPrice));
            List<Monitor> monitors = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Monitors WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        monitors.add(new Monitor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("resolution"),
                            resultSet.getInt("refresh_rate"),
                            resultSet.getDouble("size"),
                            resultSet.getString("panel_type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!monitors.isEmpty()) {
                logger.info("MonitorService.searchByPrice", String.format("Successfully fetched %d Monitors", monitors.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Monitors", monitors.size()),
                    "monitor",
                    new ArrayList<Hardware>(monitors)
                ));
            } else {
                logger.info("MonitorService.searchByPrice", "Failed to fetch Monitors");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Monitors",
                    "monitor",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MonitorService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MonitorService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Monitors",
                "monitor",
                List.of()
            ));
        }
    }
}
