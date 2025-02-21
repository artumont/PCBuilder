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
import com.pcbuilder.backend.models.auth.RegisterRequest;
import com.pcbuilder.backend.utils.Crypto;

@RestController
@RequestMapping("/auth")
public class Auth {
    private Logger logger;
    private Connection connection;
    private Crypto crypto;

    public Auth(Logger givenLogger, Connection givenConnection, Crypto givenCrypto) {
        logger = givenLogger;
        connection = givenConnection;
        crypto = givenCrypto;
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

            // @note: Log request details
            logger.info("Auth.login", String.format(
                "Request details - IP: %s, User-Agent: %s, Method: %s, URI: %s, Protocol: %s",
                clientIp,
                userAgent,
                method,
                requestURI,
                protocol
            ));

            String username = loginRequest.username();
            String password = loginRequest.password();

            // @note: Check if any of the fields are null
            if (username == null || password == null) {
                AuthResponse response = new AuthResponse(
                    "Invalid request", 
                    null, 
                    null
                );
                logger.info("UserOperation.LoginOperation", "Invalid request");
                return ResponseEntity.status(400).body(response);
            }
            logger.info("Auth.login", String.format("Login request - Username: %s, Password: %s", username, password));

            // @note: Check if user exists in database
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND hash_password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            if (statement.executeQuery().getString("username").equals(username) && statement.executeQuery().getString("hash_password").equals(password)) {
                logger.info("UserOperation.LoginOperation", "Login successful");

                String authToken = crypto.generateToken(username, password, "auth", 5000 * 60);
                String refreshToken = crypto.generateToken(username, password, "regen", 24000 * 60 * 60 * 60);
                
                // @note: Check if tokens were generated successfully
                if (authToken == null || refreshToken == null) {
                    AuthResponse response = new AuthResponse(
                        "Login successful, but failed to generate tokens", 
                        null, 
                        null
                    );
                    logger.error("Auth.login", "Failed to generate tokens");
                    return ResponseEntity.status(500).body(response);
                }

                AuthResponse response = new AuthResponse(
                    "Login successful", 
                    authToken, 
                    refreshToken
                );
                return ResponseEntity.ok(response);
            }
            else {
                // @note: Username or password incorrect
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> postMethodName(@RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        try {
            logger.info("Auth.register", "Received register request.");
            String clientIp = request.getRemoteAddr();
            if (request.getHeader("X-Forwarded-For") != null) {
                clientIp = request.getHeader("X-Forwarded-For");
            }
            String userAgent = request.getHeader("User-Agent");
            String method = request.getMethod();
            String requestURI = request.getRequestURI();
            String protocol = request.getProtocol();

            // @note: Log request details
            logger.info("Auth.register", String.format(
                "Request details - IP: %s, User-Agent: %s, Method: %s, URI: %s, Protocol: %s",
                clientIp,
                userAgent,
                method,
                requestURI,
                protocol
            ));

            String email = registerRequest.email();
            String username = registerRequest.username();
            String password = registerRequest.password();
            String phoneNumber = registerRequest.phoneNumber();

            // @note: Check if any of the fields are null
            if (email == null || username == null || password == null || phoneNumber == null) {
                AuthResponse response = new AuthResponse(
                    "Invalid request", 
                    null, 
                    null
                );
                logger.info("UserOperation.RegisterOperation", "Invalid request");
                return ResponseEntity.status(400).body(response);
            }
            logger.info("Auth.register", String.format("Register request - Email: %s, Username: %s, Password: %s, Phone Number: %s", email, username, password, phoneNumber));
            
            // @note: Check if username already exists in database
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
            statement.setString(1, email);
            statement.setString(2, username);
            if (statement.executeQuery().next()) {
                AuthResponse response = new AuthResponse(
                    "Email or username already in use", 
                    null, 
                    null
                );
                logger.info("UserOperation.RegisterOperation", "Email or username already in use");
                return ResponseEntity.status(400).body(response);
            }
            
            // @note: Insert new user into database
            statement = connection.prepareStatement("INSERT INTO Users (email, username, hash_password, phone_number) VALUES (?, ?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, phoneNumber);
            if (statement.executeUpdate() > 0) {
                logger.info("UserOperation.RegisterOperation", "Registration successful");

                String authToken = crypto.generateToken(username, password, "auth", 5000 * 60);
                String refreshToken = crypto.generateToken(username, password, "regen", 24000 * 60 * 60 * 60);

                // @note: Check if tokens were generated successfully
                if (authToken == null || refreshToken == null) {
                    AuthResponse response = new AuthResponse(
                        "Registration successful, but failed to generate tokens", 
                        null, 
                        null
                    );
                    logger.error("Auth.register", "Failed to generate tokens");
                    return ResponseEntity.status(500).body(response);
                }

                AuthResponse response = new AuthResponse(
                    "Registration successful", 
                    authToken, 
                    refreshToken
                );
                return ResponseEntity.ok(response);
            }
            else {
                // @note: Registration failed
                AuthResponse response = new AuthResponse(
                    "Registration failed", 
                    null, 
                    null
                );
                logger.info("UserOperation.RegisterOperation", "Registration failed");
                return ResponseEntity.status(400).body(response);
            }
        }
        catch (Exception e) {
            logger.error("Auth.register", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
