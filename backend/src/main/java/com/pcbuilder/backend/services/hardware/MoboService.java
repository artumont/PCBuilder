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
import com.pcbuilder.backend.models.hardware.Mobo;
import com.pcbuilder.backend.models.hardware.Hardware;
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
        try {
            logger.info("MoboService.fetchById", String.format("Fetching Motherboard with id: %s", id));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Motherboards WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("MoboService.fetchById", String.format("Found Motherboard with id: %s", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "success",
                    "Found Motherboard with id: " + id,
                    "mobo",
                    new Mobo(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
                        resultSet.getString("socket"),
                        resultSet.getInt("sata_storage_slots"),
                        resultSet.getInt("m2_storage_slots"),
                        resultSet.getInt("ram_slots"),
                        resultSet.getString("ram_type"),
                        resultSet.getString("size"),
                        resultSet.getString("chipset"), 
                        resultSet.getFloat("price")  
                    )
                ));
            }
            else {
                logger.info("MoboService.fetchById", String.format("Motherboard with id: %s not found", id));
                return ResponseEntity.ok(new HardwareResponse(
                    "error",
                    "Motherboard with id: " + id + " not found",
                    "mobo",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Motherboard",
                "mobo",
                null
            ));
        } catch (Exception e) {
            logger.error("MoboService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching Motherboard",
                "mobo",
                null
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRange(int offset, int limit) {
        try {
            logger.info("MoboService.searchByRange", String.format("Searching for Motherboards with offset: %s and limit: %s", offset, limit));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.Motherboards ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByRange", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByRange", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("MoboService.searchByName", String.format("Searching for Motherboards with name: %s", name));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE LOWER(name) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(name)), name"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%");
                statement.setString(3, name);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByName", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByName", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySocket(String socket, Integer limit) {
        try {
            logger.info("MoboService.searchBySocket", String.format("Searching for Motherboards with socket: %s", socket));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE LOWER(socket) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(socket)), socket"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + socket + "%");
                statement.setString(3, socket);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchBySocket", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchBySocket", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchBySocket", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchBySocket", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRamType(String ramType, Integer limit) {
        try {
            logger.info("MoboService.searchByRamType", String.format("Searching for Motherboards with RAM type: %s", ramType));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE LOWER(ram_type) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(ram_type)), ram_type"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + ramType + "%");
                statement.setString(3, ramType);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByRamType", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByRamType", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByRamType", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByRamType", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySize(String size, Integer limit) {
        try {
            logger.info("MoboService.searchBySize", String.format("Searching for Motherboards with size: %s", size));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE LOWER(size) LIKE LOWER(?) ORDER BY CHARINDEX(LOWER(?), LOWER(size)), size"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + size + "%");
                statement.setString(3, size);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchBySize", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchBySize", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchBySize", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchBySize", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByChipset(Integer chipsetId, Integer limit) {
        try {
            logger.info("MoboService.searchByChipset", String.format("Searching for Motherboards with chipset ID: %d", chipsetId));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE chipset_id = ? ORDER BY name"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, chipsetId);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByChipset", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByChipset", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByChipset", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByChipset", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySataSlots(Integer minSataSlots, Integer maxSataSlots, Integer limit) {
        try {
            logger.info("MoboService.searchBySataSlots", String.format("Searching for Motherboards with SATA slots between %d and %d", minSataSlots, maxSataSlots));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE sata_storage_slots >= ? AND sata_storage_slots <= ? ORDER BY sata_storage_slots"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minSataSlots);
                statement.setInt(3, maxSataSlots);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchBySataSlots", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchBySataSlots", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchBySataSlots", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchBySataSlots", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByRamSlots(Integer minRamSlots, Integer maxRamSlots, Integer limit) {
        try {
            logger.info("MoboService.searchByRamSlots", String.format("Searching for Motherboards with RAM slots between %d and %d", minRamSlots, maxRamSlots));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE ram_slots >= ? AND ram_slots <= ? ORDER BY ram_slots"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minRamSlots);
                statement.setInt(3, maxRamSlots);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByRamSlots", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByRamSlots", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByRamSlots", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByRamSlots", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByM2Slots(Integer minM2Slots, Integer maxM2Slots, Integer limit) {
        try {
            logger.info("MoboService.searchByM2Slots", String.format("Searching for Motherboards with M.2 slots between %d and %d", minM2Slots, maxM2Slots));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE m2_storage_slots >= ? AND m2_storage_slots <= ? ORDER BY m2_storage_slots"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minM2Slots);
                statement.setInt(3, maxM2Slots);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByM2Slots", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByM2Slots", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByM2Slots", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByM2Slots", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(Float minPrice, Float maxPrice, Integer limit) {
        try {
            logger.info("MoboService.searchByPrice", String.format("Searching for Motherboards with price between %f and %f", minPrice, maxPrice));
            List<Mobo> mobos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.Motherboards WHERE price >= ? AND price <= ? ORDER BY price"
            )) {
                statement.setInt(1, limit);
                statement.setFloat(2, minPrice);
                statement.setFloat(3, maxPrice);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mobos.add(new Mobo(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("sata_storage_slots"),
                            resultSet.getInt("m2_storage_slots"),
                            resultSet.getInt("ram_slots"),
                            resultSet.getString("ram_type"),
                            resultSet.getString("size"),
                            resultSet.getString("chipset"), 
                            resultSet.getFloat("price")  
                        ));
                    }
                }
            }

            if (!mobos.isEmpty()) {
                logger.info("MoboService.searchByPrice", String.format("Successfully fetched %d Motherboards", mobos.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d Motherboards", mobos.size()),
                    "mobo",
                    new ArrayList<Hardware>(mobos)
                ));
            } else {
                logger.info("MoboService.searchByPrice", "Failed to fetch Motherboards");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch Motherboards",
                    "mobo",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("MoboService.searchByPrice", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("MoboService.searchByPrice", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching Motherboards",
                "mobo",
                List.of()
            ));
        }
    }
}