package com.pcbuilder.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pcbuilder.helpers.Config;
import com.pcbuilder.helpers.Logger;

public class Database {
    private Connection connection;
    private final int maxRetryCount;
    private final int retryDelay;
    
    private static Logger logger;
    private static Config config;

    public Database(Logger givenLogger, Config givenConfig) {
        logger = givenLogger;
        config = givenConfig;

        maxRetryCount = Integer.parseInt(config.getSetting("Database", "MaxRetryCount"));
        retryDelay = Integer.parseInt(config.getSetting("Database", "RetryDelay"));
    }

    public boolean connect() {
        for (int i = 0; i < maxRetryCount; i++) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(getConnectionString(), config.getSetting("Database", "Username"), config.getSetting("Database", "Password"));
                logger.info("Database.connect", "Database connection established successfully.");
                return true;
            } catch (Exception e) {
                logger.error("Database.connect", String.format("Error connecting to database: %s", e.getMessage()));
                try {
                    logger.info("Database.connect", "Retrying connection in 1 second.");
                    Thread.sleep(retryDelay);
                } catch (InterruptedException ex) {
                    logger.error("Database.connect", String.format("Error while sleeping thread: %s", ex.getMessage()));
                }
            }
        }
        logger.critical("Database.connect", "Failed to connect to database after maximum retry count.");
        return false;
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

    private String getConnectionString() {
        return String.format(
            "jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=%s;trustServerCertificate=%s;",
            config.getSetting("Database", "ServerName"),
            config.getSetting("Database", "Port"),
            config.getSetting("Database", "DatabaseName"),
            config.getSetting("Database", "Encrypt"),
            config.getSetting("Database", "TrustServerCertificate")
        );
    }
}
