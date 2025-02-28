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
import com.pcbuilder.backend.models.hardware.Ram;
import com.pcbuilder.backend.models.hardware.Hardware;
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
        try {
            logger.info("RamService.fetchById", String.format("Fetching RAM with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.RAMs WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("RamService.fetchById", String.format("Found RAM with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found RAM with id: " + id,
                    "ram",
                    new Ram(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getInt("speed"),
                        resultSet.getInt("size"),
                        resultSet.getString("type"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("RamService.fetchById", String.format("RAM with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "RAM with id: " + id + " not found",
                    "ram",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching RAM",
                "ram",
                null
            ));
        } catch (Exception e) {
            logger.error("RamService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching RAM",
                "ram",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("RamService.searchByRange", String.format("Searching for RAMs with offset: %s and limit: %s", offset, limit));
            List<Ram> rams = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.RAMs ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        rams.add(new Ram(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("speed"),
                            resultSet.getInt("size"),
                            resultSet.getString("type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!rams.isEmpty()) {
                logger.info("RamService.searchByRange", String.format("Successfully fetched %d RAMs", rams.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d RAMs", rams.size()),
                    "ram",
                    new ArrayList<Hardware>(rams)
                ));
            } else {
                logger.info("RamService.searchByRange", "Failed to fetch RAMs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch RAMs",
                    "ram",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("RamService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("RamService.searchByName", String.format("Searching for RAMs with name: %s", name));
            List<Ram> rams = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.RAMs WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        rams.add(new Ram(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("speed"),
                            resultSet.getInt("size"),
                            resultSet.getString("type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!rams.isEmpty()) {
                logger.info("RamService.searchByName", String.format("Successfully fetched %d RAMs", rams.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d RAMs", rams.size()),
                    "ram",
                    new ArrayList<Hardware>(rams)
                ));
            } else {
                logger.info("RamService.searchByName", "Failed to fetch RAMs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch RAMs",
                    "ram",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("RamService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySpeed(Integer minSpeed, Integer maxSpeed, Integer limit) {
        try {
            logger.info("RamService.searchBySpeed", String.format("Searching for RAMs with speed between %d and %d", minSpeed, maxSpeed));
            List<Ram> rams = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.RAMs WHERE speed >= ? AND speed <= ? ORDER BY speed"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minSpeed);
                statement.setInt(3, maxSpeed);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        rams.add(new Ram(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("speed"),
                            resultSet.getInt("size"),
                            resultSet.getString("type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!rams.isEmpty()) {
                logger.info("RamService.searchBySpeed", String.format("Successfully fetched %d RAMs", rams.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d RAMs", rams.size()),
                    "ram",
                    new ArrayList<Hardware>(rams)
                ));
            } else {
                logger.info("RamService.searchBySpeed", "Failed to fetch RAMs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch RAMs",
                    "ram",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.searchBySpeed", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("RamService.searchBySpeed", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByCapacity(Integer minCapacity, Integer maxCapacity, Integer limit) {
        try {
            logger.info("RamService.searchByCapacity", String.format("Searching for RAMs with capacity between %d and %d", minCapacity, maxCapacity));
            List<Ram> rams = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.RAMs WHERE size >= ? AND size <= ? ORDER BY size"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minCapacity);
                statement.setInt(3, maxCapacity);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        rams.add(new Ram(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("speed"),
                            resultSet.getInt("size"),
                            resultSet.getString("type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!rams.isEmpty()) {
                logger.info("RamService.searchByCapacity", String.format("Successfully fetched %d RAMs", rams.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d RAMs", rams.size()),
                    "ram",
                    new ArrayList<Hardware>(rams)
                ));
            } else {
                logger.info("RamService.searchByCapacity", "Failed to fetch RAMs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch RAMs",
                    "ram",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.searchByCapacity", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("RamService.searchByCapacity", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRamType(String ramType, Integer limit) {
        try {
            logger.info("RamService.searchByRamType", String.format("Searching for RAMs with type: %s", ramType));
            List<Ram> rams = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.RAMs WHERE LOWER(type) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(type)), type"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + ramType + "%"); 
                statement.setString(3, ramType);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        rams.add(new Ram(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("speed"),
                            resultSet.getInt("size"),
                            resultSet.getString("type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!rams.isEmpty()) {
                logger.info("RamService.searchByRamType", String.format("Successfully fetched %d RAMs", rams.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d RAMs", rams.size()),
                    "ram",
                    new ArrayList<Hardware>(rams)
                ));
            } else {
                logger.info("RamService.searchByRamType", "Failed to fetch RAMs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch RAMs",
                    "ram",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.searchByRamType", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("RamService.searchByRamType", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("RamService.searchByPrice", String.format("Searching for RAMs with price between %f and %f", minPrice, maxPrice));
            List<Ram> rams = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.RAMs WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        rams.add(new Ram(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("speed"),
                            resultSet.getInt("size"),
                            resultSet.getString("type"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!rams.isEmpty()) {
                logger.info("RamService.searchByPrice", String.format("Successfully fetched %d RAMs", rams.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d RAMs", rams.size()),
                    "ram",
                    new ArrayList<Hardware>(rams)
                ));
            } else {
                logger.info("RamService.searchByPrice", "Failed to fetch RAMs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch RAMs",
                    "ram",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("RamService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("RamService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching RAMs",
                "ram",
                List.of()
            ));
        }
    }
}
