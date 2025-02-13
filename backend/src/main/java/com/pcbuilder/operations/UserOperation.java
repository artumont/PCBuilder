package com.pcbuilder.operations;

import org.json.JSONObject;
import java.sql.Connection;
import io.jsonwebtoken.Claims;
import java.sql.PreparedStatement;

import com.pcbuilder.helpers.Crypto;
import com.pcbuilder.helpers.Logger;

public class UserOperation {
    private static Logger logger;
    private static Connection connection;

    public UserOperation(Logger givenLogger) {
        logger = givenLogger;
    }

    public String interpretOperationType(JSONObject args, Connection givenConnection) {
        JSONObject response = new JSONObject();

        if (givenConnection == null) {
            logger.error("UserOperation.interpretOperationType", "Database connection is null. Cannot interpret operation type.");
            response.clear();
            response.put("status", "error");
            response.put("message", "Database connection is null");
            return response.toString();
        }

        connection = givenConnection;
        try {
            logger.info("UserOperation.interpretOperationType", String.format("Interpreting operation type: %s", args.optString("operationType", "NotProvided")));
            switch (args.optString("operationType", "NotProvided")) {
                case "Login":
                    logger.info("UserOperation.interpretOperationType", "Login operation identified.");
                    return LoginOperation(
                        args.optString("username", "").replaceAll("[^A-Za-z0-9]", ""), 
                        args.optString("password", "").replaceAll("[^A-Za-z0-9@#$%^&+=]", "")
                    );
                case "Register":
                    logger.info("UserOperation.interpretOperationType", "Register operation identified.");
                    return RegisterOperation(
                        args.optString("email", "").replaceAll("[^A-Za-z0-9+_.@-]", ""),
                        args.optString("username", "").replaceAll("[^A-Za-z0-9]", ""), 
                        args.optString("password", "").replaceAll("[^A-Za-z0-9@#$%^&+=]", "")
                    );
                case "RegenAuthToken":
                    logger.info("UserOperation.interpretOperationType", "Regen auth token operation identified.");
                    return RegenAuthToken(args.optString("regen-token", ""));
                case "ReplaceRegenToken":
                    logger.info("UserOperation.interpretOperationType", "Replace regen token operation identified.");
                    return ReplaceRegenToken(args.optString("regen-token", ""));
                default: 
                    logger.error("UserOperation.interpretOperationType", String.format("Unknown operation type: %s", args.optString("type")));
                    response.clear();
                    response.put("status", "error");
                    response.put("message", "Unknown operation type");
                    return response.toString();
                case "NotProvided":
                    logger.error("UserOperation.interpretOperationType", "Operation type not provided");
                    response.clear();
                    response.put("status", "error");
                    response.put("message", "Operation type not provided");
                    return response.toString();
            }
        }
        catch (Exception e) {
            logger.error("UserOperation.interpretOperationType", String.format("Error interpreting operation type: %s", e.getMessage()));
            response.clear();
            response.put("status", "error");
            response.put("message", "Error interpreting operation type");
            return response.toString();
        }
    }

    private String LoginOperation(String username, String password) {
        JSONObject response = new JSONObject();

        if (username == "" || password == "") {
            response.clear();
            response.put("status", "error");
            response.put("message", "Username or password not provided");
            logger.error("UserOperation.LoginOperation", "Username or password not provided");
            return response.toString();
        }

        try {
            logger.info("UserOperation.LoginOperation", String.format("Attempting login with username: %s", username));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Username = ? AND Password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            if (statement.executeQuery().next()) {
                logger.info("UserOperation.LoginOperation", "Login successful");
                response.clear();
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("auth-token", Crypto.generateToken(username, password, "auth", 5000 * 60));
                response.put("regen-token" , Crypto.generateToken(username, password, "regen", 24000 * 60 * 60 * 60));
                return response.toString();
            }
            else {
                logger.error("UserOperation.LoginOperation", "Invalid username or password");
                response.clear();
                response.put("status", "error");
                response.put("message", "Invalid username or password");
                return response.toString();
            }
        }
        catch (Exception e) {
            logger.error("UserOperation.LoginOperation", String.format("Error during login operation: %s", e.getMessage()));
            response.clear();
            response.put("status", "error");
            response.put("message", "Error during login operation");
            logger.error("UserOperation.LoginOperation", "Error during login operation");
            return response.toString();
        }
    }

    private String RegisterOperation(String email, String username, String password) {
        JSONObject response = new JSONObject();

        if (username == null || password == null) {
            logger.error("UserOperation.RegisterOperation", "Username or password not provided");
            response.clear();
            response.put("status", "error");
            response.put("message", "Username or password not provided");
            return response.toString();
        }

        try {
            logger.info("UserOperation.RegisterOperation", String.format("Attempting registration with username: %s", username));
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (Email, Username, Password) VALUES (?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            if (statement.executeUpdate() > 0) {
                logger.info("UserOperation.RegisterOperation", "Registration successful");
                response.clear();
                response.put("status", "success");
                response.put("message", "Registration successful");
                return response.toString();
            }
            else {
                logger.error("UserOperation.RegisterOperation", "Error during registration");
                response.clear();
                response.put("status", "error");
                response.put("message", "Error during registration");
                return response.toString();
            }
        }
        catch (Exception e) {
            logger.error("UserOperation.RegisterOperation", String.format("Error during register operation: %s", e.getMessage()));
            response.clear();
            response.put("status", "error");
            response.put("message", "Error during register operation");
            return response.toString();
        }
    }

    private String RegenAuthToken(String regenToken) {
        JSONObject response = new JSONObject();

        if (regenToken == null) {
            logger.error("UserOperation.RegenAuthToken", "Regen token not provided");
            response.clear();
            response.put("status", "error");
            response.put("message", "Regen token not provided");
            return response.toString();
        }

        try {
            logger.info("UserOperation.RegenAuthToken", "Attempting to regen auth token");
            Claims claims = Crypto.verifyToken(regenToken, "regen");
            if (claims != null) {
                logger.info("UserOperation.RegenAuthToken", "Regen token verified");
                response.clear();
                response.put("status", "success");
                response.put("message", "Regen token verified");
                response.put("auth-token", Crypto.generateToken(claims.getSubject(), claims.getIssuer(), "auth", 5000 * 60));
                return response.toString();
            }
            else {
                logger.error("UserOperation.RegenAuthToken", "Invalid regen token");
                response.clear();
                response.put("status", "error");
                response.put("message", "Invalid regen token");
                return response.toString();
            }
        }
        catch (Exception e) {
            logger.error("UserOperation.RegenAuthToken", String.format("Error during regen token operation: %s", e.getMessage()));
            response.clear();
            response.put("status", "error");
            response.put("message", "Error during regen token operation");
            return response.toString();
        }
    }

    private String ReplaceRegenToken(String regenToken) {
        JSONObject response = new JSONObject();

        if (regenToken == null) {
            logger.error("UserOperation.ReplaceRegenToken", "Regen token not provided");
            response.clear();
            response.put("status", "error");
            response.put("message", "Regen token not provided");
            return response.toString();
        }

        try {
            logger.info("UserOperation.ReplaceRegenToken", "Attempting to replace regen token");
            Claims claims = Crypto.verifyToken(regenToken, "regen");
            if (claims != null) {
                logger.info("UserOperation.ReplaceRegenToken", "Regen token verified");
                response.clear();
                response.put("status", "success");
                response.put("message", "Regen token verified");
                response.put("regen-token", Crypto.generateToken(claims.getSubject(), claims.getIssuer(), "regen", 24000 * 60 * 60 * 60));
                return response.toString();
            }
            else {
                logger.error("UserOperation.ReplaceRegenToken", "Invalid regen token");
                response.clear();
                response.put("status", "error");
                response.put("message", "Invalid regen token");
                return response.toString();
            }
        }
        catch (Exception e) {
            logger.error("UserOperation.ReplaceRegenToken", String.format("Error during replace regen token operation: %s", e.getMessage()));
            response.clear();
            response.put("status", "error");
            response.put("message", "Error during replace regen token operation");
            return response.toString();
        }
    }
}
