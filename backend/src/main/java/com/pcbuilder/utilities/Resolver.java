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

    public enum Operation {
        UserOperation("UserOperation"),
        ComponentOperation("ComponentOperation");
        
        private final String value;

        Operation(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Operation fromValue(String value) {
            for (Operation operation : Operation.values()) {
                if (operation.value.equals(value)) {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Unknown operation: " + value);
        }
    }

    public Resolver(Logger givenLogger) {
        logger = givenLogger;
        componentOperation = new ComponentOperation(logger);
        userOperation = new UserOperation(logger);
    }

    public String resolveOperation(Operation operation, String args) {
        if (connection == null) {
            logger.error("Resolver.resolveOperation", "Database connection is null. Cannot resolve operation.");
            return "Database connection is null";
        }

        try {
            JSONObject jsonArgs = new JSONObject(args);
            switch (operation) {
                case UserOperation:
                    logger.info("Resolver.resolveOperation", String.format("User operation resolved with args: %s", args));
                    //@todo: Implement user operation & replace return statement
                    return "User operation resolved";
                case ComponentOperation:
                    logger.info("Resolver.resolveOperation", String.format("Component operation resolved with args: %s", args));
                    //@todo: Implement components operation & replace return statement
                    return "Component operation resolved";
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
