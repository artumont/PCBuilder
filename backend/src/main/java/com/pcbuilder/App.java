package com.pcbuilder;

import com.pcbuilder.helpers.Logger;
import com.pcbuilder.helpers.Config;
import com.pcbuilder.utilities.Database;
import com.pcbuilder.utilities.Resolver;
import com.pcbuilder.utilities.Sockets;

public class App {
    private static Logger logger = new Logger();

    public static void main(String[] args) throws Exception {
        logger.setLevel(Logger.LogLevel.INFO);
        Config config = new Config(logger, getCWDString() + "\\config.ini");

        if (Boolean.getBoolean("debug.enabled")) {
            logger.setLevel(Logger.LogLevel.DEBUG);
        } else {
            logger.setLevel(Logger.LogLevel.getLevel(config.getSetting("Logging", "LogLevel")));
        }

        Database database = new Database(logger, config);
        Resolver resolver = new Resolver(logger);
        Sockets server = new Sockets(logger, resolver);
        
        logger.info("App.main", String.format("Starting server on port %s with log level %s", config.getSetting("Server", "Port"), logger.getLevel().toString()));
        if (server.start(Integer.parseInt(config.getSetting("Server", "Port")))) {
            logger.info("App.main", "Socket server started successfully, attempting to start critical services.");

            serviceStartupAssurance(database.connect(), "Database");
            serviceStartupAssurance(resolver.updateDatabaseConnection(database.getConnection()), "Resolver");

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
        if (!serviceStatus) {
            logger.critical("App.main", String.format("Service '%s' initialization failed. Exiting application.", serviceName));
            System.exit(1);
        }
        else {
            logger.info("App.main", String.format("Service '%s' started successfully", serviceName));
        }
    }

    private static String getCWDString() {
        String currentPath = System.getProperty("user.dir");
        return currentPath;
    }
}
