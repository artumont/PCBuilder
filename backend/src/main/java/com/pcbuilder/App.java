package com.pcbuilder;

import com.pcbuilder.helpers.Logger;
import com.pcbuilder.helpers.Config;
import com.pcbuilder.utilities.Database;
import com.pcbuilder.utilities.Sockets;

public class App {
    private static Logger logger = new Logger();

    public static void main(String[] args) throws Exception {
        Config config = new Config(logger, "config.ini");
        logger.info("App.main", "Application started.");

        if (Boolean.getBoolean("debug.enabled")) {
            logger.setLevel(Logger.LogLevel.DEBUG);
        } else {
            logger.setLevel(Logger.LogLevel.getLevel(config.getSetting("Logging", "LogLevel")));
        }

        Sockets server = new Sockets(logger);
        Database database = new Database(logger, config);
        
        if (server.start(Integer.parseInt(config.getSetting("Server", "Port")))) {
            logger.info("App.main", "Socket server started successfully, attempting to start critical services.");

            serviceStartupAssurance(database.connect(), "Database");

            logger.info("App.main", "All critical services started successfully, listening for incoming connections.");
            server.startListening();
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("App.main", "Shutting down server and disconnecting from database.");
                database.disconnect();
                server.stop();
            }));
        } else {
            logger.critical("App.main", "Failed to start socket server. Exiting application.");
            System.exit(1);
        }
    }

    private static void serviceStartupAssurance(boolean serviceStatus, String serviceName) {
        logger.info("App.main", "Starting service: " + serviceName);
        if (!serviceStatus) {
            logger.critical("App.main", String.format("Service '%s' initialization failed. Exiting application.", serviceName));
            System.exit(1);
        }
        else {
            logger.info("App.main", String.format("Service '%s' started successfully", serviceName));
        }
    }
}
