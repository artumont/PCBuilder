package com.pcbuilder.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pcbuilder.helpers.Config;
import com.pcbuilder.helpers.Logger;

public class Database {
    private Connection connection;
    
    private static Logger logger;
    private static Config config;

    public Database(Logger givenLogger, Config givenConfig) {
        logger = givenLogger;
        config = givenConfig;
    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(config.getSetting("Database", "ConnectionString"));
            return true;
        } catch (SQLException e) {
            logger.error("Database.connect", String.format("Error connecting to database: %s", e.getMessage()));
            return false;
        }
    }


    public boolean disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                return true;
            }
            else {
                logger.warning("Database.close", "Connection is already closed.");
                return true;
            }
        } catch (SQLException e) {
            logger.error("Database.close", String.format("Error closing database connection: %s", e.getMessage()));
            return false;
        }
    }
}
