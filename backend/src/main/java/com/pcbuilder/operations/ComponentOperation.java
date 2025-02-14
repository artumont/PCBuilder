package com.pcbuilder.operations;

import java.sql.Connection;

import org.json.JSONObject;

import com.pcbuilder.helpers.Logger;

public class ComponentOperation {
    private static Logger logger;
    private static Connection connection;

    public ComponentOperation(Logger givenLogger) {
        logger = givenLogger;
    }

    public String interpretOperationType(JSONObject args, Connection givenConnection) {
        JSONObject response = new JSONObject();

        if (givenConnection == null) {
            logger.error("ComponentOperation.interpretOperationType", "Database connection is null. Cannot interpret operation type.");
            response.clear();
            response.put("status", "error");
            response.put("message", "Database connection is null");
            return response.toString();
        }

        connection = givenConnection;
        try {
            switch (args.optString("operationType", "NotProvided")) {
                case "Get":
                    logger.info("ComponentOperation.interpretOperationType", "Get operation identified.");
                    return ""; // @todo: implement
                case "Add":
                    logger.info("ComponentOperation.interpretOperationType", "Add operation identified.");
                    return ""; // @todo: implement
                default: 
                    logger.error("ComponentOperation.interpretOperationType", String.format("Unknown operation type: %s", args.optString("type")));
                    response.clear();
                    response.put("status", "error");
                    response.put("message", "Unknown operation type");
                    return response.toString();
                case "NotProvided":
                    logger.error("ComponentOperation.interpretOperationType", "Operation type not provided");
                    response.clear();
                    response.put("status", "error");
                    response.put("message", "Operation type not provided");
                    return response.toString();
            }
        }
        catch (Exception e) {
            logger.error("ComponentOperation.interpretOperationType", String.format("Error interpreting operation type: %s", e.getMessage()));
            response.clear();
            response.put("status", "error");
            response.put("message", "Error interpreting operation type");
            return response.toString();
        }
    }
}
