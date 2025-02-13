package com.pcbuilder.operations;

import java.sql.Connection;

import org.json.JSONObject;

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
                    String username = args.optString("username");
                    String password = args.optString("password");
                    return LoginOperation(username, password);
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
            return "PlaceHolder";
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
            return "PlaceHolder";
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
