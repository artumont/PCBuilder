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
import com.pcbuilder.backend.models.hardware.Storage;
import com.pcbuilder.backend.models.hardware.Hardware;
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
        try {
            logger.info("StorageService.fetchById", String.format("Fetching Storage with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Storage WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("StorageService.fetchById", String.format("Found Storage with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found Storage with id: " + id,
                    "storage",
                    new Storage(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("format"),
                        resultSet.getString("protocol"),
                        resultSet.getInt("size"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("StorageService.fetchById", String.format("Storage with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "Storage with id: " + id + " not found",
                    "storage",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Storage",
                "storage",
                null
            ));
        } catch (Exception e) {
            logger.error("StorageService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Storage",
                "storage",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("StorageService.searchByRange", String.format("Searching for Storage devices with offset: %s and limit: %s", offset, limit));
            List<Storage> storageList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Storage ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        storageList.add(new Storage(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("format"),
                            resultSet.getString("protocol"),
                            resultSet.getInt("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!storageList.isEmpty()) {
                logger.info("StorageService.searchByRange", String.format("Successfully fetched %d Storage devices", storageList.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Storage devices", storageList.size()),
                    "storage",
                    new ArrayList<Hardware>(storageList)
                ));
            } else {
                logger.info("StorageService.searchByRange", "Failed to fetch Storage devices");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Storage devices",
                    "storage",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("StorageService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("StorageService.searchByName", String.format("Searching for Storage devices with name: %s", name));
            List<Storage> storageList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Storage WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        storageList.add(new Storage(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("format"),
                            resultSet.getString("protocol"),
                            resultSet.getInt("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!storageList.isEmpty()) {
                logger.info("StorageService.searchByName", String.format("Successfully fetched %d Storage devices", storageList.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Storage devices", storageList.size()),
                    "storage",
                    new ArrayList<Hardware>(storageList)
                ));
            } else {
                logger.info("StorageService.searchByName", "Failed to fetch Storage devices");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Storage devices",
                    "storage",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("StorageService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByStorageFormat(String format, Integer limit) {
        try {
            logger.info("StorageService.searchByStorageFormat", String.format("Searching for Storage devices with format: %s", format));
            List<Storage> storageList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Storage WHERE LOWER(format) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(format)), format"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + format + "%"); 
                statement.setString(3, format);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        storageList.add(new Storage(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("format"),
                            resultSet.getString("protocol"),
                            resultSet.getInt("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!storageList.isEmpty()) {
                logger.info("StorageService.searchByStorageFormat", String.format("Successfully fetched %d Storage devices", storageList.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Storage devices", storageList.size()),
                    "storage",
                    new ArrayList<Hardware>(storageList)
                ));
            } else {
                logger.info("StorageService.searchByStorageFormat", "Failed to fetch Storage devices");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Storage devices",
                    "storage",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.searchByStorageFormat", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("StorageService.searchByStorageFormat", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByStorageProtocol(String protocol, Integer limit) {
        try {
            logger.info("StorageService.searchByStorageProtocol", String.format("Searching for Storage devices with protocol: %s", protocol));
            List<Storage> storageList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Storage WHERE LOWER(protocol) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(protocol)), protocol"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + protocol + "%"); 
                statement.setString(3, protocol);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        storageList.add(new Storage(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("format"),
                            resultSet.getString("protocol"),
                            resultSet.getInt("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!storageList.isEmpty()) {
                logger.info("StorageService.searchByStorageProtocol", String.format("Successfully fetched %d Storage devices", storageList.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Storage devices", storageList.size()),
                    "storage",
                    new ArrayList<Hardware>(storageList)
                ));
            } else {
                logger.info("StorageService.searchByStorageProtocol", "Failed to fetch Storage devices");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Storage devices",
                    "storage",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.searchByStorageProtocol", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("StorageService.searchByStorageProtocol", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySize(int minSize, int maxSize, Integer limit) {
        try {
            logger.info("StorageService.searchBySize", String.format("Searching for Storage devices with size between %d and %d", minSize, maxSize));
            List<Storage> storageList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Storage WHERE size >= ? AND size <= ? ORDER BY size"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minSize);
                statement.setInt(3, maxSize);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        storageList.add(new Storage(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("format"),
                            resultSet.getString("protocol"),
                            resultSet.getInt("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!storageList.isEmpty()) {
                logger.info("StorageService.searchBySize", String.format("Successfully fetched %d Storage devices", storageList.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Storage devices", storageList.size()),
                    "storage",
                    new ArrayList<Hardware>(storageList)
                ));
            } else {
                logger.info("StorageService.searchBySize", "Failed to fetch Storage devices");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Storage devices",
                    "storage",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.searchBySize", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("StorageService.searchBySize", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("StorageService.searchByPrice", String.format("Searching for Storage devices with price between %f and %f", minPrice, maxPrice));
            List<Storage> storageList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Storage WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        storageList.add(new Storage(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("format"),
                            resultSet.getString("protocol"),
                            resultSet.getInt("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!storageList.isEmpty()) {
                logger.info("StorageService.searchByPrice", String.format("Successfully fetched %d Storage devices", storageList.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Storage devices", storageList.size()),
                    "storage",
                    new ArrayList<Hardware>(storageList)
                ));
            } else {
                logger.info("StorageService.searchByPrice", "Failed to fetch Storage devices");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Storage devices",
                    "storage",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("StorageService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("StorageService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Storage devices",
                "storage",
                List.of()
            ));
        }
    }
}
