package com.pcbuilder.operations;

import org.json.JSONObject;
import java.sql.Connection;
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
            switch (args.optString("type", "NotProvided")) {
                case "Login":
                    logger.info("UserOperation.interpretOperationType", "Login operation identified.");
                    return LoginOperation(
                        args.optString("username").replaceAll("[^A-Za-z0-9]", ""), 
                        args.optString("password").replaceAll("[^A-Za-z0-9@#$%^&+=]", "")
                    );
                case "Register":
                    logger.info("UserOperation.interpretOperationType", "Register operation identified.");
                    return RegisterOperation(
                        args.optString("email").replaceAll("[^A-Za-z0-9+_.@-]", ""),
                        args.optString("username").replaceAll("[^A-Za-z0-9]", ""), 
                        args.optString("password").replaceAll("[^A-Za-z0-9@#$%^&+=]", "")
                    );
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

        if (username == null || password == null) {
            response.clear();
            response.put("status", "error");
            response.put("message", "Username or password not provided");
            return response.toString();
        }

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Username = ? AND Password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            if (statement.executeQuery().next()) {
                response.clear();
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("auth-token", Crypto.generateAuthToken(username, password, 5000 * 60));
                return response.toString();
            }
            else {
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
            return response.toString();
        }
    }

    private String RegisterOperation(String email, String username, String password) {
        JSONObject response = new JSONObject();

        if (username == null || password == null) {
            response.clear();
            response.put("status", "error");
            response.put("message", "Username or password not provided");
            return response.toString();
        }

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (Email, Username, Password) VALUES (?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            if (statement.executeUpdate() > 0) {
                response.clear();
                response.put("status", "success");
                response.put("message", "Registration successful");
                return response.toString();
            }
            else {
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
}
