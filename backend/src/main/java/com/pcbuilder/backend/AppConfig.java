package com.pcbuilder.backend;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pcbuilder.backend.utils.Config;
import com.pcbuilder.backend.utils.Crypto;
import com.pcbuilder.backend.utils.Database;
import com.pcbuilder.backend.utils.Logger;

@Configuration
public class AppConfig {

    @Bean
    public Logger logger() {
        Logger logger = new Logger(Logger.LogLevel.INFO, true);
        return logger;
    }

    @Bean
    public Config config(Logger logger) {
        Config config = new Config(logger, getCWDString() + "\\config.ini");
        return config;
    }

    @Bean
    public Connection connection(Logger logger, Config config) {
        Database database = new Database(logger, config);
        boolean connectionStatus = database.connect();
        serviceStartupAssurance(logger, connectionStatus, "Database");
        return database.getConnection();
    }

    @Bean
    public Crypto crypto(Logger logger, Config config) {
        Crypto crypto = new Crypto(logger, config);
        return crypto;
    }

    private static String getCWDString() {
        String currentPath = System.getProperty("user.dir");
        return currentPath;
    }

    private static void serviceStartupAssurance(Logger logger, boolean serviceStatus, String serviceName) {
        if (!serviceStatus) {
            logger.critical("App.main", String.format("Service '%s' initialization failed. Exiting application.", serviceName));
            System.exit(1);
        } else {
            logger.info("App.main", String.format("Service '%s' started successfully", serviceName));
        }
    }
}