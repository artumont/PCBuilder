package com.pcbuilder.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pcbuilder.backend.helpers.Database;
import com.pcbuilder.backend.helpers.Logger;
import com.pcbuilder.backend.utils.Config;

@SpringBootApplication
public class App {
	private static Logger logger;

	public static void main(String[] args) {
		
		Logger logger = new Logger(Logger.LogLevel.INFO, true);
		Config config = new Config(logger, getCWDString() + "\\config.ini");

		if (Boolean.getBoolean("debug.enabled")) {
            logger.setLevel(Logger.LogLevel.DEBUG);
        } else {
            logger.setLevel(Logger.LogLevel.getLevel(config.getSetting("Logging", "LogLevel")));
        }

		Database database = new Database(logger, config);
		logger.info("App.main", "Starting application.");

		serviceStartupAssurance(database.connect(), "Database");
		
				SpringApplication.run(App.class, args);
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
