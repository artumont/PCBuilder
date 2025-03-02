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
import com.pcbuilder.backend.models.hardware.Cooler;
import com.pcbuilder.backend.models.hardware.Hardware;
import com.pcbuilder.backend.utils.Logger;

@Service
public class CoolerService {
    private final Logger logger;
    private final Connection connection;

    public CoolerService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        try {
            logger.info("CoolerService.fetchById", String.format("Fetching Cooler with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Coolers WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("CoolerService.fetchById", String.format("Found Cooler with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found Cooler with id: " + id,
                    "cooler",
                    new Cooler(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("socket"),
                        resultSet.getString("image_url"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("CoolerService.fetchById", String.format("Cooler with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "Cooler with id: " + id + " not found",
                    "cooler",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("CoolerService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Cooler",
                "cooler",
                null
            ));
        } catch (Exception e) {
            logger.error("CoolerService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Cooler",
                "cooler",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("CoolerService.searchByRange", String.format("Searching for Coolers with offset: %s and limit: %s", offset, limit));
            List<Cooler> coolers = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Coolers ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        coolers.add(new Cooler(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("socket"),
                            resultSet.getString("image_url"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!coolers.isEmpty()) {
                logger.info("CoolerService.searchByRange", String.format("Successfully fetched %d Coolers", coolers.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Coolers", coolers.size()),
                    "cooler",
                    new ArrayList<Hardware>(coolers)
                ));
            } else {
                logger.info("CoolerService.searchByRange", "Failed to fetch Coolers");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Coolers",
                    "cooler",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CoolerService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CoolerService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("CoolerService.searchByName", String.format("Searching for Coolers with name: %s", name));
            List<Cooler> coolers = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Coolers WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        coolers.add(new Cooler(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("socket"),
                            resultSet.getString("image_url"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!coolers.isEmpty()) {
                logger.info("CoolerService.searchByName", String.format("Successfully fetched %d Coolers", coolers.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Coolers", coolers.size()),
                    "cooler",
                    new ArrayList<Hardware>(coolers)
                ));
            } else {
                logger.info("CoolerService.searchByName", "Failed to fetch Coolers");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Coolers",
                    "cooler",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CoolerService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CoolerService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySocket(String socket, Integer limit) {
        try {
            logger.info("CoolerService.searchBySocket", String.format("Searching for Coolers with socket: %s", socket));
            List<Cooler> coolers = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Coolers WHERE LOWER(socket) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(socket)), socket"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + socket + "%"); 
                statement.setString(3, socket);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        coolers.add(new Cooler(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("socket"),
                            resultSet.getString("image_url"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!coolers.isEmpty()) {
                logger.info("CoolerService.searchBySocket", String.format("Successfully fetched %d Coolers", coolers.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Coolers", coolers.size()),
                    "cooler",
                    new ArrayList<Hardware>(coolers)
                ));
            } else {
                logger.info("CoolerService.searchBySocket", "Failed to fetch Coolers");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Coolers",
                    "cooler",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CoolerService.searchBySocket", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CoolerService.searchBySocket", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("CoolerService.searchByPrice", String.format("Searching for Coolers with price between %f and %f", minPrice, maxPrice));
            List<Cooler> coolers = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Coolers WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        coolers.add(new Cooler(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("socket"),
                            resultSet.getString("image_url"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!coolers.isEmpty()) {
                logger.info("CoolerService.searchByPrice", String.format("Successfully fetched %d Coolers", coolers.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Coolers", coolers.size()),
                    "cooler",
                    new ArrayList<Hardware>(coolers)
                ));
            } else {
                logger.info("CoolerService.searchByPrice", "Failed to fetch Coolers");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Coolers",
                    "cooler",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CoolerService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CoolerService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Coolers",
                "cooler",
                List.of()
            ));
        }
    }
}
