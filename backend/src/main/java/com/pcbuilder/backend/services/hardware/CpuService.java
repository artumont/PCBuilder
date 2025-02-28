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
import com.pcbuilder.backend.models.hardware.Cpu;
import com.pcbuilder.backend.models.hardware.Hardware;
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
        } catch (SQLException e) {
            logger.error("CpuService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new HardwareResponse(
                "error",
                "Internal server error while fetching CPU",
                "cpu",
                null
            ));
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
            List<Cpu> cpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hardware.CPUs ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")) {
                statement.setInt(1, offset);
                statement.setInt(2, limit);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cpus.add(new Cpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("cores"),
                            resultSet.getFloat("clock_speed"),
                            resultSet.getInt("threads"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cpus.isEmpty()) {
                logger.info("CpuService.searchByRange", String.format("Successfully fetched %d CPUs", cpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d CPUs", cpus.size()),
                    "cpu",
                    new ArrayList<Hardware>(cpus)
                ));
            } else {
                logger.info("CpuService.searchByRange", "Failed to fetch CPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch CPUs",
                    "cpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CpuService.searchByRange", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("CpuService.searchByRange", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByName(String name, Integer limit) {
        try {
            logger.info("CpuService.searchByName", String.format("Searching for CPUs with name: %s", name));
            List<Cpu> cpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.CPUs WHERE LOWER(name) LIKE LOWER(?) ORDER BY id"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + name + "%"); 
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cpus.add(new Cpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("cores"),
                            resultSet.getFloat("clock_speed"),
                            resultSet.getInt("threads"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cpus.isEmpty()) {
                logger.info("CpuService.searchByName", String.format("Successfully fetched %d CPUs", cpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d CPUs", cpus.size()),
                    "cpu",
                    new ArrayList<Hardware>(cpus)
                ));
            } else {
                logger.info("CpuService.searchByName", "Failed to fetch CPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch CPUs",
                    "cpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CpuService.searchByName", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("CpuService.searchByName", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchBySocket(String socket, Integer limit) {
        try {
            logger.info("CpuService.searchBySocket", String.format("Searching for CPUs with socket: %s", socket));
            List<Cpu> cpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.CPUs WHERE LOWER(socket) LIKE LOWER(?) ORDER BY id"
            )) {
                statement.setInt(1, limit);
                statement.setString(2, "%" + socket + "%"); 
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cpus.add(new Cpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("cores"),
                            resultSet.getFloat("clock_speed"),
                            resultSet.getInt("threads"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cpus.isEmpty()) {
                logger.info("CpuService.searchBySocket", String.format("Successfully fetched %d CPUs", cpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d CPUs", cpus.size()),
                    "cpu",
                    new ArrayList<Hardware>(cpus)
                ));
            } else {
                logger.info("CpuService.searchBySocket", "Failed to fetch CPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch CPUs",
                    "cpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CpuService.searchBySocket", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("CpuService.searchBySocket", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByCores(Integer minCores, Integer maxCores, Integer limit) {
        try {
            logger.info("CpuService.searchByCores", String.format("Searching for CPUs with cores between %d and %d", minCores, maxCores));
            List<Cpu> cpus = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT TOP (?) * FROM Hardware.CPUs WHERE cores >= ? AND cores <= ? ORDER BY id"
            )) {
                statement.setInt(1, limit);
                statement.setInt(2, minCores);
                statement.setInt(3, maxCores);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        cpus.add(new Cpu(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getString("socket"),
                            resultSet.getInt("cores"),
                            resultSet.getFloat("clock_speed"),
                            resultSet.getInt("threads"),
                            resultSet.getFloat("price")
                        ));
                    }
                }
            }

            if (!cpus.isEmpty()) {
                logger.info("CpuService.searchByCores", String.format("Successfully fetched %d CPUs", cpus.size()));
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "success",
                    String.format("Successfully fetched %d CPUs", cpus.size()),
                    "cpu",
                    new ArrayList<Hardware>(cpus)
                ));
            } else {
                logger.info("CpuService.searchByCores", "Failed to fetch CPUs");
                return ResponseEntity.ok(new MultiHardwareResponse(
                    "error",
                    "Failed to fetch CPUs",
                    "cpu",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("CpuService.searchByCores", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
        catch (Exception e) {
            logger.error("CpuService.searchByCores", e.getMessage());
            return ResponseEntity.status(500).body(new MultiHardwareResponse(
                "error",
                "Internal server error while fetching CPUs",
                "cpu",
                List.of()
            ));
        }
    }

    public ResponseEntity<MultiHardwareResponse> searchByClockSpeed(float minClockSpeed, float maxClockSpeed, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByClockSpeed'");
    }

    public ResponseEntity<MultiHardwareResponse> searchByPrice(float minPrice, float maxPrice, Integer limit) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByPrice'");
    }
}