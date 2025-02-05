package com.pcbuilder.helpers;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class Config {
    private Ini ini;
    private static Logger logger;

    public Config (Logger givenLogger, String path) {
        try {
            logger = givenLogger;
            File configFile = new File(path);

            if (path == null || path.isEmpty()) {
                logger.error("Config", "Path to config file must be specified.");
                return;
            }
            else if (!configFile.exists()) {
                logger.error("Config", "Config file does not exist.");
                if (!create(path)) {
                    logger.error("Config", "Failed to create config file.");
                    return;
                }
                else {
                    configFile = new File(path);
                }
            }

            ini = new Ini(configFile);
            logger.info("Config", "Config file loaded successfully.");
        } catch (InvalidFileFormatException e) {
            givenLogger.error("Config", e.getMessage());
        } catch (IOException e) {
            givenLogger.error("Config", e.getMessage());
        }
    }

    public String getSetting(String section, String key) {
        logger.debug("Config.getSetting", String.format("Retrieved setting '%s' from section '%s'", key, section));
        return ini.get(section, key);
    }

    public boolean setSetting(String section, String key, String value) {
        logger.debug("Config.setSetting", String.format("Set setting '%s' in section '%s' to '%s'", key, section, value));
        ini.put(section, key, value);
        return save();
    }

    private boolean save() {
        try {
            ini.store();
            logger.info("Config.save", "Config file saved successfully.");
            return true;
        } catch (IOException e) {
            logger.error("Config.save", String.format("Failed to save config file: %s'.", e.getMessage()));
            return false;
        }
    }

    private boolean create(String path) {
        try {
            Ini newIni = new Ini();

            // Logging section
            newIni.put("Logging", "LogLevel", "INFO");
            newIni.put("Logging", "LogPath", "./logs");
            
            // Server section
            newIni.put("Server", "Port", "9312");
            newIni.put("Server", "MaxConnections", "10");
            newIni.put("Server", "SocketTimeout", "30000");
            
            // Database section
            newIni.put("Database", "ServerName", "localhost");
            newIni.put("Database", "Port", "1433");
            newIni.put("Database", "DatabaseName", "PCBuilder");
            newIni.put("Database", "Encrypt", "true");
            newIni.put("Database", "TrustServerCertificate", "true");
            newIni.put("Database", "Username", "sa");
            newIni.put("Database", "Password", "pcbuilder");
            newIni.put("Database", "MaxRetryCount", "3");
            newIni.put("Database", "RetryDelay", "1000");
            
            // Save to file
            newIni.store(new File(path));
            logger.info("Config.create", "Config file created successfully.");
            return true;
        } catch (IOException e) {
            logger.error("Config.create", String.format("Failed to create config file: %s", e.getMessage()));
            return false;
        }
    }

    public static String getCWDString() {
        String currentPath = System.getProperty("user.dir");
        return currentPath;
    }
}
