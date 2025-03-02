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
import com.pcbuilder.backend.models.hardware.Case;
import com.pcbuilder.backend.models.hardware.Hardware;
import com.pcbuilder.backend.utils.Logger;

@Service
public class CaseService {
    private final Logger logger;
    private final Connection connection;

    public CaseService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        try {
            logger.info("CaseService.fetchById", String.format("Fetching Case with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Cases WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("CaseService.fetchById", String.format("Found Case with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found Case with id: " + id,
                    "case",
                    new Case(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("size"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("CaseService.fetchById", String.format("Case with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "Case with id: " + id + " not found",
                    "case",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("CaseService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Case",
                "case",
                null
            ));
        } catch (Exception e) {
            logger.error("CaseService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Case",
                "case",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("CaseService.searchByRange", String.format("Searching for Cases with offset: %s and limit: %s", offset, limit));
            List<Case> cases = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Cases ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cases.add(new Case(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cases.isEmpty()) {
                logger.info("CaseService.searchByRange", String.format("Successfully fetched %d Cases", cases.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Cases", cases.size()),
                    "case",
                    new ArrayList<Hardware>(cases)
                ));
            } else {
                logger.info("CaseService.searchByRange", "Failed to fetch Cases");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Cases",
                    "case",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CaseService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CaseService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("CaseService.searchByName", String.format("Searching for Cases with name: %s", name));
            List<Case> cases = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Cases WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cases.add(new Case(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cases.isEmpty()) {
                logger.info("CaseService.searchByName", String.format("Successfully fetched %d Cases", cases.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Cases", cases.size()),
                    "case",
                    new ArrayList<Hardware>(cases)
                ));
            } else {
                logger.info("CaseService.searchByName", "Failed to fetch Cases");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Cases",
                    "case",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CaseService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CaseService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySize(String size, Integer limit) {
        try {
            logger.info("CaseService.searchBySize", String.format("Searching for Cases with size: %s", size));
            List<Case> cases = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Cases WHERE LOWER(size) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(size)), size"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + size + "%"); 
                statement.setString(3, size);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cases.add(new Case(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cases.isEmpty()) {
                logger.info("CaseService.searchBySize", String.format("Successfully fetched %d Cases", cases.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Cases", cases.size()),
                    "case",
                    new ArrayList<Hardware>(cases)
                ));
            } else {
                logger.info("CaseService.searchBySize", "Failed to fetch Cases");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Cases",
                    "case",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CaseService.searchBySize", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CaseService.searchBySize", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("CaseService.searchByPrice", String.format("Searching for Cases with price between %f and %f", minPrice, maxPrice));
            List<Case> cases = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Cases WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cases.add(new Case(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cases.isEmpty()) {
                logger.info("CaseService.searchByPrice", String.format("Successfully fetched %d Cases", cases.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Cases", cases.size()),
                    "case",
                    new ArrayList<Hardware>(cases)
                ));
            } else {
                logger.info("CaseService.searchByPrice", "Failed to fetch Cases");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Cases",
                    "case",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CaseService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CaseService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Cases",
                "case",
                List.of()
            ));
        }
    }
}
