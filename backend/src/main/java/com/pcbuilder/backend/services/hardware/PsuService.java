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
import com.pcbuilder.backend.models.hardware.Psu;
import com.pcbuilder.backend.models.hardware.Hardware;
import com.pcbuilder.backend.utils.Logger;

@Service
public class PsuService {
    private final Logger logger;
    private final Connection connection;

    public PsuService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        try {
            logger.info("PsuService.fetchById", String.format("Fetching PSU with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.PSUs WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("PsuService.fetchById", String.format("Found PSU with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found PSU with id: " + id,
                    "psu",
                    new Psu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getInt("wattage"),
                        resultSet.getString("size"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("PsuService.fetchById", String.format("PSU with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "PSU with id: " + id + " not found",
                    "psu",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("PsuService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching PSU",
                "psu",
                null
            ));
        } catch (Exception e) {
            logger.error("PsuService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching PSU",
                "psu",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("PsuService.searchByRange", String.format("Searching for PSUs with offset: %s and limit: %s", offset, limit));
            List<Psu> psus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.PSUs ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        psus.add(new Psu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("wattage"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!psus.isEmpty()) {
                logger.info("PsuService.searchByRange", String.format("Successfully fetched %d PSUs", psus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d PSUs", psus.size()),
                    "psu",
                    new ArrayList<Hardware>(psus)
                ));
            } else {
                logger.info("PsuService.searchByRange", "Failed to fetch PSUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch PSUs",
                    "psu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("PsuService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("PsuService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("PsuService.searchByName", String.format("Searching for PSUs with name: %s", name));
            List<Psu> psus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.PSUs WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%");
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        psus.add(new Psu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("wattage"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!psus.isEmpty()) {
                logger.info("PsuService.searchByName", String.format("Successfully fetched %d PSUs", psus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d PSUs", psus.size()),
                    "psu",
                    new ArrayList<Hardware>(psus)
                ));
            } else {
                logger.info("PsuService.searchByName", "Failed to fetch PSUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch PSUs",
                    "psu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("PsuService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("PsuService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByWattage(int minWattage, int maxWattage, Integer limit) {
        try {
            logger.info("PsuService.searchByWattage", String.format("Searching for PSUs with wattage between %d and %d", minWattage, maxWattage));
            List<Psu> psus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.PSUs WHERE wattage >= ? AND wattage <= ? ORDER BY wattage"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minWattage);
                statement.setInt(3, maxWattage);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        psus.add(new Psu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("wattage"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!psus.isEmpty()) {
                logger.info("PsuService.searchByWattage", String.format("Successfully fetched %d PSUs", psus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d PSUs", psus.size()),
                    "psu",
                    new ArrayList<Hardware>(psus)
                ));
            } else {
                logger.info("PsuService.searchByWattage", "Failed to fetch PSUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch PSUs",
                    "psu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("PsuService.searchByWattage", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("PsuService.searchByWattage", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySize(String size, Integer limit) {
        try {
            logger.info("PsuService.searchBySize", String.format("Searching for PSUs with size: %s", size));
            List<Psu> psus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.PSUs WHERE LOWER(size) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(size)), size"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + size + "%");
                statement.setString(3, size);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        psus.add(new Psu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("wattage"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!psus.isEmpty()) {
                logger.info("PsuService.searchBySize", String.format("Successfully fetched %d PSUs", psus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d PSUs", psus.size()),
                    "psu",
                    new ArrayList<Hardware>(psus)
                ));
            } else {
                logger.info("PsuService.searchBySize", "Failed to fetch PSUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch PSUs",
                    "psu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("PsuService.searchBySize", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("PsuService.searchBySize", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("PsuService.searchByPrice", String.format("Searching for PSUs with price between %f and %f", minPrice, maxPrice));
            List<Psu> psus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.PSUs WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        psus.add(new Psu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("wattage"),
                            resultSet.getString("size"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!psus.isEmpty()) {
                logger.info("PsuService.searchByPrice", String.format("Successfully fetched %d PSUs", psus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d PSUs", psus.size()),
                    "psu",
                    new ArrayList<Hardware>(psus)
                ));
            } else {
                logger.info("PsuService.searchByPrice", "Failed to fetch PSUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch PSUs",
                    "psu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("PsuService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("PsuService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching PSUs",
                "psu",
                List.of()
            ));
        }
    }
}
