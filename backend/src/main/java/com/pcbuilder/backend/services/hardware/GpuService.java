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
import com.pcbuilder.backend.models.hardware.Gpu;
import com.pcbuilder.backend.models.hardware.Hardware;
import com.pcbuilder.backend.utils.Logger;

@Service
public class GpuService {
    private final Logger logger;
    private final Connection connection;

    public GpuService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<HardwareResponse> fetchById(int id) {
        try {
            logger.info("GpuService.fetchById", String.format("Fetching GPU with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.GPUs WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("GpuService.fetchById", String.format("Found GPU with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found GPU with id: " + id,
                    "gpu",
                    new Gpu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("chipset"),
                        resultSet.getInt("vram"),
                        resultSet.getInt("wattage"),
                        resultSet.getFloat("price")
                    )
                ));
            }
            else {
                logger.info("GpuService.fetchById", String.format("GPU with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "GPU with id: " + id + " not found",
                    "gpu",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching GPU",
                "gpu",
                null
            ));
        } catch (Exception e) {
            logger.error("GpuService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching GPU",
                "gpu",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("GpuService.searchByRange", String.format("Searching for GPUs with offset: %s and limit: %s", offset, limit));
            List<Gpu> gpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.GPUs ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        gpus.add(new Gpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("chipset"),
                            resultSet.getInt("vram"),
                            resultSet.getInt("wattage"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!gpus.isEmpty()) {
                logger.info("GpuService.searchByRange", String.format("Successfully fetched %d GPUs", gpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d GPUs", gpus.size()),
                    "gpu",
                    new ArrayList<Hardware>(gpus)
                ));
            } else {
                logger.info("GpuService.searchByRange", "Failed to fetch GPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch GPUs",
                    "gpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("GpuService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("GpuService.searchByName", String.format("Searching for GPUs with name: %s", name));
            List<Gpu> gpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.GPUs WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        gpus.add(new Gpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("chipset"),
                            resultSet.getInt("vram"),
                            resultSet.getInt("wattage"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!gpus.isEmpty()) {
                logger.info("GpuService.searchByName", String.format("Successfully fetched %d GPUs", gpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d GPUs", gpus.size()),
                    "gpu",
                    new ArrayList<Hardware>(gpus)
                ));
            } else {
                logger.info("GpuService.searchByName", "Failed to fetch GPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch GPUs",
                    "gpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("GpuService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByChipset(String chipset, Integer limit) {
        try {
            logger.info("GpuService.searchByChipset", String.format("Searching for GPUs with chipset: %s", chipset));
            List<Gpu> gpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.GPUs WHERE LOWER(chipset) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(chipset)), chipset"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + chipset + "%"); 
                statement.setString(3, chipset);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        gpus.add(new Gpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("chipset"),
                            resultSet.getInt("vram"),
                            resultSet.getInt("wattage"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!gpus.isEmpty()) {
                logger.info("GpuService.searchByChipset", String.format("Successfully fetched %d GPUs", gpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d GPUs", gpus.size()),
                    "gpu",
                    new ArrayList<Hardware>(gpus)
                ));
            } else {
                logger.info("GpuService.searchByChipset", "Failed to fetch GPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch GPUs",
                    "gpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.searchByChipset", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("GpuService.searchByChipset", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByVram(Integer minVram, Integer maxVram, Integer limit) {
        try {
            logger.info("GpuService.searchByVram", String.format("Searching for GPUs with VRAM between %d and %d", minVram, maxVram));
            List<Gpu> gpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.GPUs WHERE vram >= ? AND vram <= ? ORDER BY vram"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minVram);
                statement.setInt(3, maxVram);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        gpus.add(new Gpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("chipset"),
                            resultSet.getInt("vram"),
                            resultSet.getInt("wattage"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!gpus.isEmpty()) {
                logger.info("GpuService.searchByVram", String.format("Successfully fetched %d GPUs", gpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d GPUs", gpus.size()),
                    "gpu",
                    new ArrayList<Hardware>(gpus)
                ));
            } else {
                logger.info("GpuService.searchByVram", "Failed to fetch GPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch GPUs",
                    "gpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.searchByVram", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("GpuService.searchByVram", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByWattage(Integer minWattage, Integer maxWattage, Integer limit) {
        try {
            logger.info("GpuService.searchByWattage", String.format("Searching for GPUs with wattage between %d and %d", minWattage, maxWattage));
            List<Gpu> gpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.GPUs WHERE wattage >= ? AND wattage <= ? ORDER BY wattage"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minWattage);
                statement.setInt(3, maxWattage);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        gpus.add(new Gpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("chipset"),
                            resultSet.getInt("vram"),
                            resultSet.getInt("wattage"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!gpus.isEmpty()) {
                logger.info("GpuService.searchByWattage", String.format("Successfully fetched %d GPUs", gpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d GPUs", gpus.size()),
                    "gpu",
                    new ArrayList<Hardware>(gpus)
                ));
            } else {
                logger.info("GpuService.searchByWattage", "Failed to fetch GPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch GPUs",
                    "gpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.searchByWattage", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("GpuService.searchByWattage", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        try {
            logger.info("GpuService.searchByPrice", String.format("Searching for GPUs with price between %f and %f", minPrice, maxPrice));
            List<Gpu> gpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.GPUs WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        gpus.add(new Gpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("chipset"),
                            resultSet.getInt("vram"),
                            resultSet.getInt("wattage"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!gpus.isEmpty()) {
                logger.info("GpuService.searchByPrice", String.format("Successfully fetched %d GPUs", gpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d GPUs", gpus.size()),
                    "gpu",
                    new ArrayList<Hardware>(gpus)
                ));
            } else {
                logger.info("GpuService.searchByPrice", "Failed to fetch GPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch GPUs",
                    "gpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("GpuService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("GpuService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching GPUs",
                "gpu",
                List.of()
            ));
        }
    }
}
