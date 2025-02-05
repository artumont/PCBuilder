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
                if (!create()) {
                    logger.error("Config", "Failed to create config file.");
                    return;
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

    private boolean create() {
        try {
            ini.put("Socket", "port", "9854");
            ini.put("Database", "connectionUrl", "jdbc:mysql://localhost:3306/pcbuilder");
            ini.put("Database", "username", "root");
            ini.put("Database", "password", "password");
            ini.put("Logging", "minimumLevel", "INFO");
            Ini newIni = new Ini(new File("./config.ini"));
            newIni.store();
            return true;
        } catch (IOException e) {
            logger.error("Config.create", String.format("Failed to create config file: %s", e.getMessage()));
            return false;
        }
    }
}
