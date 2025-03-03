package com.pcbuilder.backend.controllers.user;

import java.sql.Connection;

import io.jsonwebtoken.Claims;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcbuilder.backend.dto.user.AddConfigRequest;
import com.pcbuilder.backend.dto.user.AddConfigResponse;
import com.pcbuilder.backend.dto.user.DeleteConfigRequest;
import com.pcbuilder.backend.dto.user.DeleteConfigResponse;
import com.pcbuilder.backend.dto.user.GetConfigRequest;
import com.pcbuilder.backend.dto.user.GetConfigResponse;
import com.pcbuilder.backend.dto.user.MultiConfigResponse;
import com.pcbuilder.backend.services.user.ConfigService;
import com.pcbuilder.backend.utils.Crypto;
import com.pcbuilder.backend.utils.Logger;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger;
    private final ConfigService configService;
    private final Crypto crypto;

    public UserController(Logger givenLogger, Connection givenConnection, Crypto givenCrypto, ConfigService configService) {
        this.logger = givenLogger;
        this.crypto = givenCrypto;
        this.configService = configService;
    }

    private int getUserIdFromToken(String authToken) throws Exception {
        Claims claims = crypto.verifyToken(authToken, "auth");
        if (claims != null) {
            return Integer.parseInt(claims.getId());
        }
        return -999;
    }

    @PostMapping("/get/configs")
    public ResponseEntity<MultiConfigResponse> getConfigs(@RequestBody AddConfigRequest configRequest, HttpServletRequest request) {
        try {
            int userId = getUserIdFromToken(configRequest.authToken());
            if (userId == -999) {
                return ResponseEntity.status(401).body(new MultiConfigResponse(
                    "error",
                    "Invalid auth token",
                    null
                ));
            }
            return configService.fetchByUserId(userId);
        } catch (Exception e) {
            logger.error("UserController.getConfigs", e.getMessage());
            return ResponseEntity.status(500).body(new MultiConfigResponse(
                "error",
                "Internal server error while fetching configs",
                null
            ));
        }
    }

    @PostMapping("/get/config")
    public ResponseEntity<GetConfigResponse> getConfig(@RequestBody GetConfigRequest configRequest, HttpServletRequest request) {
        try {
            int userId = getUserIdFromToken(configRequest.authToken());
            if (userId == -999) {
                return ResponseEntity.status(401).body(new GetConfigResponse(
                    "error",
                    "Invalid auth token",
                    null
                ));
            }
            return configService.fetchById(configRequest.id(), userId);
        } catch (Exception e) {
            logger.error("UserController.getConfig", e.getMessage());
            return ResponseEntity.status(500).body(new GetConfigResponse(
                "error",
                "Internal server error while fetching config",
                null
            ));
        }
    }

    @PostMapping("/add/config")
    public ResponseEntity<AddConfigResponse> addConfig(@RequestBody AddConfigRequest configRequest, HttpServletRequest request) {
        try {
            int userId = getUserIdFromToken(configRequest.authToken());
            if (userId == -999) {
                return ResponseEntity.status(401).body(new AddConfigResponse(
                    "error",
                    "Invalid auth token"
                ));
            }
            return configService.addConfig(configRequest.config(), userId);
        } catch (Exception e) {
            logger.error("UserController.addConfig", e.getMessage());
            return ResponseEntity.status(500).body(new AddConfigResponse(
                "error",
                "Internal server error while adding config"
            ));
        }
    }

    @PostMapping("/delete/config")
    public ResponseEntity<DeleteConfigResponse> deleteConfig(@RequestBody DeleteConfigRequest configRequest, HttpServletRequest request) {
        try {
            int userId = getUserIdFromToken(configRequest.authToken());
            if (userId == -999) {
                return ResponseEntity.status(401).body(new DeleteConfigResponse(
                    "error",
                    "Invalid auth token"
                ));
            }
            return configService.deleteConfig(configRequest.id(), userId);
        } catch (Exception e) {
            logger.error("UserController.deleteConfig", e.getMessage());
            return ResponseEntity.status(500).body(new DeleteConfigResponse(
                "error",
                "Internal server error while deleting config"
            ));
        }
    }
}
