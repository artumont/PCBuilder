package com.pcbuilder.backend.services.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcbuilder.backend.dto.user.AddConfigResponse;
import com.pcbuilder.backend.dto.user.DeleteConfigResponse;
import com.pcbuilder.backend.dto.user.GetConfigResponse;
import com.pcbuilder.backend.dto.user.MultiConfigResponse;
import com.pcbuilder.backend.models.user.ConfigObj;
import com.pcbuilder.backend.utils.Logger;

@Service
public class ConfigService {
    private final Logger logger;
    private final Connection connection;

    public ConfigService(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public ResponseEntity<GetConfigResponse> fetchById(int configId, int userId) {
        try {
            logger.info("ConfigService.fetchById", String.format("Fetching config with id: %d for user: %d", configId, userId));
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Users.Configs WHERE id = ? AND user_id = ?"
            );
            statement.setInt(1, configId);
            statement.setInt(2, userId);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("ConfigService.fetchById", String.format("Found config with id: %d", configId));
                return ResponseEntity.ok(new GetConfigResponse(
                    "success",
                    "Found config with id: " + configId,
                    new ConfigObj(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("config_data"),
                        resultSet.getString("creation_date")
                    )
                ));
            } else {
                logger.info("ConfigService.fetchById", String.format("Config with id: %d not found", configId));
                return ResponseEntity.ok(new GetConfigResponse(
                    "error",
                    "Config with id: " + configId + " not found",
                    null
                ));
            }
        } catch (SQLException e) {
            logger.error("ConfigService.fetchById", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new GetConfigResponse(
                "error",
                "Internal server error while fetching config",
                null
            ));
        } catch (Exception e) {
            logger.error("ConfigService.fetchById", e.getMessage());
            return ResponseEntity.status(500).body(new GetConfigResponse(
                "error",
                "Internal server error while fetching config",
                null
            ));
        }
    }

    public ResponseEntity<AddConfigResponse> addConfig(String configData, int userId) {
        try {
            logger.info("ConfigService.addConfig", String.format("Adding new config for user: %d", userId));
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Users.Configs (user_id, config_data) VALUES (?, ?)"
            );
            statement.setInt(1, userId);
            statement.setString(2, configData);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("ConfigService.addConfig", "Successfully added new config");
                return ResponseEntity.ok(new AddConfigResponse(
                    "success",
                    "Successfully added new config"
                ));
            } else {
                logger.error("ConfigService.addConfig", "Failed to add new config");
                return ResponseEntity.ok(new AddConfigResponse(
                    "error",
                    "Failed to add new config"
                ));
            }
        } catch (SQLException e) {
            logger.error("ConfigService.addConfig", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new AddConfigResponse(
                "error",
                "Internal server error while adding config"
            ));
        } catch (Exception e) {
            logger.error("ConfigService.addConfig", e.getMessage());
            return ResponseEntity.status(500).body(new AddConfigResponse(
                "error",
                "Internal server error while adding config"
            ));
        }
    }

    public ResponseEntity<MultiConfigResponse> fetchByUserId(int userId) {
        try {
            logger.info("ConfigService.fetchByUserId", String.format("Fetching configs for user: %d", userId));
            List<ConfigObj> configList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Users.Configs WHERE user_id = ? ORDER BY creation_date DESC"
            );
            statement.setInt(1, userId);
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                configList.add(new ConfigObj(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("config_data"),
                    resultSet.getString("creation_date")
                ));
            }

            if (!configList.isEmpty()) {
                logger.info("ConfigService.fetchByUserId", String.format("Found %d configs for user: %d", configList.size(), userId));
                return ResponseEntity.ok(new MultiConfigResponse(
                    "success",
                    String.format("Found %d configs", configList.size()),
                    configList
                ));
            } else {
                logger.info("ConfigService.fetchByUserId", String.format("No configs found for user: %d", userId));
                return ResponseEntity.ok(new MultiConfigResponse(
                    "error",
                    "No configs found",
                    List.of()
                ));
            }
        } catch (SQLException e) {
            logger.error("ConfigService.fetchByUserId", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new MultiConfigResponse(
                "error",
                "Internal server error while fetching configs",
                List.of()
            ));
        } catch (Exception e) {
            logger.error("ConfigService.fetchByUserId", e.getMessage());
            return ResponseEntity.status(500).body(new MultiConfigResponse(
                "error",
                "Internal server error while fetching configs",
                List.of()
            ));
        }
    }

    public ResponseEntity<DeleteConfigResponse> deleteConfig(int configId, int userId) {
        try {
            logger.info("ConfigService.deleteConfig", String.format("Deleting config with id: %d for user: %d", configId, userId));
            PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Users.Configs WHERE id = ? AND user_id = ?"
            );
            statement.setInt(1, configId);
            statement.setInt(2, userId);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("ConfigService.deleteConfig", String.format("Successfully deleted config with id: %d", configId));
                return ResponseEntity.ok(new DeleteConfigResponse(
                    "success",
                    "Successfully deleted config with id: " + configId
                ));
            } else {
                logger.info("ConfigService.deleteConfig", String.format("Config with id: %d not found or doesn't belong to user", configId));
                return ResponseEntity.ok(new DeleteConfigResponse(
                    "error",
                    "Config not found or unauthorized to delete"
                ));
            }
        } catch (SQLException e) {
            logger.error("ConfigService.deleteConfig", String.format("Database error: %s", e.getMessage()));
            return ResponseEntity.status(500).body(new DeleteConfigResponse(
                "error",
                "Internal server error while deleting config"
            ));
        } catch (Exception e) {
            logger.error("ConfigService.deleteConfig", e.getMessage());
            return ResponseEntity.status(500).body(new DeleteConfigResponse(
                "error",
                "Internal server error while deleting config"
            ));
        }
    }
}
