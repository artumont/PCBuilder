package com.pcbuilder.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pcbuilder.backend.helpers.Logger;
import com.pcbuilder.backend.utils.Config;

@SpringBootApplication
public class App {
    private static Logger logger;
    private static Config config;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        logger = context.getBean(Logger.class);
        config = context.getBean(Config.class);

        if (Boolean.getBoolean("debug.enabled")) {
            logger.setLevel(Logger.LogLevel.DEBUG);
        } else {
            logger.setLevel(Logger.LogLevel.getLevel(config.getSetting("Logging", "LogLevel")));
        }
    }
}