package com.pcbuilder.backend.controllers.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcbuilder.backend.dto.auth.AuthResponse;
import com.pcbuilder.backend.dto.auth.LoginRequest;
import com.pcbuilder.backend.dto.auth.RegisterRequest;
import com.pcbuilder.backend.utils.Crypto;
import com.pcbuilder.backend.utils.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger;
    private Connection connection;
    private Crypto crypto;

    public AuthController(Logger givenLogger, Connection givenConnection, Crypto givenCrypto) {
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
                    "error",
                    "Invalid request", 
                    null, 
                    null
                );
                logger.info("Auth.Login", "Invalid request");
                return ResponseEntity.status(400).body(response);
            }
            logger.info("Auth.login", String.format("Login request - Username: %s, Password: %s", username, password));

            // @note: Check if user exists in database
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String dbUsername = resultSet.getString("username");
                String dbPassword = resultSet.getString("password");
                
                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    logger.info("Auth.Login", "Login successful");

                    String authToken = crypto.generateToken(username, password, "auth", 5000 * 60);
                    String refreshToken = crypto.generateToken(username, password, "regen", 24000 * 60 * 60 * 60);
                    
                    // @note: Check if tokens were generated successfully
                    if (authToken == null || refreshToken == null) {
                        AuthResponse response = new AuthResponse(
                            "error",
                            "Login successful, but failed to generate tokens, please try again in a few minutes", 
                            null, 
                            null
                        );
                        logger.error("Auth.login", "Failed to generate tokens");
                        return ResponseEntity.status(500).body(response);
                    }

                    AuthResponse response = new AuthResponse(
                        "success",
                        "Login successful", 
                        authToken, 
                        refreshToken
                    );
                    return ResponseEntity.ok(response);
                }
                else {
                    // @note: Username or password incorrect
                    AuthResponse response = new AuthResponse(
                        "error",
                        "Username or password incorrect", 
                        null, 
                        null
                    );
                    logger.info("Auth.Login", "Username or password incorrect");
                    return ResponseEntity.status(401).body(response);
                }
            }
            else {
                // @note: User doesn't exist in database
                AuthResponse response = new AuthResponse(
                    "error",
                    "Username or password incorrect",
                    null, 
                    null
                );
                logger.info("Auth.Login", "User doesn't exist in database");
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

            // @note: Check if any of the fields are null
            if (email == null || username == null || password == null) {
                AuthResponse response = new AuthResponse(
                    "error",
                    "Invalid request", 
                    null, 
                    null
                );
                logger.info("Auth.RegisterOperation", "Invalid request");
                return ResponseEntity.status(400).body(response);
            }
            logger.info("Auth.register", String.format("Register request - Email: %s, Username: %s, Password: %s", email, username, password));
            
            // @note: Check if username already exists in database
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? OR email = ?");
            statement.setString(1, username);
            statement.setString(2, email);
            if (statement.executeQuery().next()) {
                AuthResponse response = new AuthResponse(
                    "error",
                    "Email or username already in use", 
                    null, 
                    null
                );
                logger.info("Auth.RegisterOperation", "Email or username already in use");
                return ResponseEntity.status(400).body(response);
            }
            
            // @note: Insert new user into database
            statement = connection.prepareStatement("INSERT INTO Users (email, username, password) VALUES (?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            if (statement.executeUpdate() > 0) {
                logger.info("Auth.RegisterOperation", "Registration successful");

                String authToken = crypto.generateToken(username, password, "auth", 5000 * 60);
                String refreshToken = crypto.generateToken(username, password, "regen", 24000 * 60 * 60 * 60);

                // @note: Check if tokens were generated successfully
                if (authToken == null || refreshToken == null) {
                    AuthResponse response = new AuthResponse(
                        "error",
                        "Registration successful, but failed to generate tokens, please try again in a few minutes", 
                        null, 
                        null
                    );
                    logger.error("Auth.register", "Failed to generate tokens");
                    return ResponseEntity.status(500).body(response);
                }

                AuthResponse response = new AuthResponse(
                    "success",
                    "Registration successful", 
                    authToken, 
                    refreshToken
                );
                return ResponseEntity.ok(response);
            }
            else {
                // @note: Registration failed
                AuthResponse response = new AuthResponse(
                    "error",
                    "Registration failed", 
                    null, 
                    null
                );
                logger.info("Auth.RegisterOperation", "Registration failed");
                return ResponseEntity.status(400).body(response);
            }
        }
        catch (Exception e) {
            logger.error("Auth.register", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
