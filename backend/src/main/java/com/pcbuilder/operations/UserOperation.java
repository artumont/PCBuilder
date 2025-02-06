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
        connection = givenConnection;
        try {
            switch (args.optString("type", "NotProvided")) {
                //@todo: Implement the operation types
                default: 
                    logger.error("UserOperation.interpretOperationType", String.format("Unknown operation type: %s", args.optString("type")));
                    return "Unknown operation type";
                case "NotProvided":
                    logger.error("UserOperation.interpretOperationType", "Operation type not provided");
                    return "Operation type not provided";
            }
        }
        catch (Exception e) {
            logger.error("ComponentOperation.interpretOperationType", String.format("Error interpreting operation type: %s", e.getMessage()));
            return "Error interpreting operation type";
        }
    }
}
