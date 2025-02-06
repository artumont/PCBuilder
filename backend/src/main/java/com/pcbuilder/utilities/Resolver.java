package com.pcbuilder.utilities;

import java.sql.Connection;
import org.json.JSONObject;

import com.pcbuilder.helpers.Logger;
import com.pcbuilder.operations.ComponentOperation;
import com.pcbuilder.operations.UserOperation;

public class Resolver {
    private static Logger logger;
    private static Connection connection;

    private static ComponentOperation componentOperation;
    private static UserOperation userOperation;

    public Resolver(Logger givenLogger) {
        logger = givenLogger;
        componentOperation = new ComponentOperation(logger);
        userOperation = new UserOperation(logger);
    }

    public String resolveOperation(String operation, String args) {
        if (connection == null) {
            logger.error("Resolver.resolveOperation", "Database connection is null. Cannot resolve operation.");
            return "Database connection is null";
        }

        try {
            JSONObject jsonArgs = new JSONObject(args);
            switch (operation) {
                case "UserOperation":
                    logger.info("Resolver.resolveOperation", String.format("User operation resolved with args: %s", args));
                    return userOperation.interpretOperationType(jsonArgs, connection);
                case "ComponentOperation":
                    logger.info("Resolver.resolveOperation", String.format("Component operation resolved with args: %s", args));
                    return componentOperation.interpretOperationType(jsonArgs, connection);
                default:
                    return "Unknown operation";
            }
        }
        catch (Exception e) {
            logger.error("Resolver.resolveOperation", String.format("Error parsing args: %s", e.getMessage()));
            return "Error parsing args";
        }
    }
    
    public boolean updateDatabaseConnection(Connection givenConnection) {
        if (givenConnection != null) {
            connection = givenConnection;
            logger.info("Resolver.updateDatabaseConnection", "Database connection updated successfully.");
            return true;
        }
        else {
            logger.error("Resolver.updateDatabaseConnection", "Database connection is null.");
            return false;
        }
    }
}
