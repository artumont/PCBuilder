package com.pcbuilder.backend.routes;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcbuilder.backend.helpers.Logger;
import com.pcbuilder.backend.models.auth.AuthResponse;
import com.pcbuilder.backend.models.auth.LoginRequest;
import com.pcbuilder.backend.utils.Crypto;

@RestController
@RequestMapping("/auth")
public class Auth {
    private Logger logger;
    private Connection connection;

    public Auth(Logger givenLogger, Connection givenConnection) {
        logger = givenLogger;
        connection = givenConnection;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            logger.info("Auth.login", "Received login request.");
            String clientIp = request.getRemoteAddr();
            if (request.getHeader("X-Forwarded-For") != null) {
                clientIp = request.getHeader("X-Forwarded-For");
            }
            String userAgent = request.getHeader("User-Agent");
            String method = request.getMethod();
            String requestURI = request.getRequestURI();
            String protocol = request.getProtocol();

            logger.debug("Auth.login", String.format(
                "Request details - IP: %s, User-Agent: %s, Method: %s, URI: %s, Protocol: %s",
                clientIp,
                userAgent,
                method,
                requestURI,
                protocol
            ));

            String username = loginRequest.username();
            String password = loginRequest.password();
            logger.info("Auth.login", String.format("Login request - Username: %s, Password: %s", username, password));

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND hash_password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            if (statement.executeQuery().next()) {
                logger.info("UserOperation.LoginOperation", "Login successful");
                AuthResponse response = new AuthResponse(
                    "Login successful", 
                    Crypto.generateToken(username, password, "auth", 5000 * 60), 
                    Crypto.generateToken(username, password, "regen", 24000 * 60 * 60 * 60)
                );
                return ResponseEntity.ok(response);
            }
            else {
                AuthResponse response = new AuthResponse(
                    "Username or password incorrect", 
                    null, 
                    null
                );
                logger.info("UserOperation.LoginOperation", "Username or password incorrect");
                return ResponseEntity.status(401).body(response);
            }
        }
        catch (Exception e) {
            logger.error("Auth.login", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
