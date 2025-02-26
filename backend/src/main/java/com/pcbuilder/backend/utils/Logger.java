package com.pcbuilder.backend.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private File logFile;
    private LogLevel minimumLevel;
    private static boolean logToFile;
    private static final String LOG_FORMAT = "[%s] %s - %s: %s%n";

    public enum LogLevel {
        DEBUG(1),
        INFO(2),
        WARNING(3),
        CRITICAL(4),
        ERROR(5);

        private final int severity;

        LogLevel(int severity) {
            this.severity = severity;
        }

        public int getSeverity() {
            return severity;
        }

        public static LogLevel getLevel(String level) {
            switch (level.toLowerCase()) {
                case "debug":
                    return LogLevel.DEBUG;
                case "info":
                    return LogLevel.INFO;
                case "warning":
                    return LogLevel.WARNING;
                case "critical":
                    return LogLevel.CRITICAL;
                case "error":
                    return LogLevel.ERROR;
                default:
                    return LogLevel.INFO;
            }
        }
    }

    public Logger(LogLevel level, boolean fileLogging) {
        minimumLevel = level;
        logToFile = fileLogging;

        if (logToFile) {
            // Create file and dir if not exists
            logFile = new File(String.format("log-%s.log", java.time.LocalDate.now()));
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    System.err.printf("Failed to create log file: %s%n", e.getMessage());
                }
            }
        }
    }

    public void setLevel(LogLevel level) {
        minimumLevel = level;
    }

    public LogLevel getLevel() {
        return minimumLevel;
    }

    private void fileLoggingHandler(String message) {
        if (logToFile && logFile != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
                writer.print(message);
            } catch (IOException e) {
                System.err.printf("Failed to write to log file: %s%n", e.getMessage());
            }
        }
    }

    public void debug(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.DEBUG.getSeverity()) {
            String logMessage = String.format(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.DEBUG,
                methodName,
                message);
            System.out.print(logMessage);
            fileLoggingHandler(logMessage);
        }
    }

    public void info(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.INFO.getSeverity()) {
            String logMessage = String.format(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.INFO,
                methodName,
                message);
            System.out.print(logMessage);
            fileLoggingHandler(logMessage);
        }
    }

    public void warning(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.WARNING.getSeverity()) {
            String logMessage = String.format(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.WARNING,
                methodName,
                message);
            System.out.print(logMessage);
            fileLoggingHandler(logMessage);
        }
    }

    public void error(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.ERROR.getSeverity()) {
            String logMessage = String.format(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.ERROR,
                methodName,
                message);
            System.out.print(logMessage);
            fileLoggingHandler(logMessage);
        }
    }
    
    public void critical(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.CRITICAL.getSeverity()) {
            String logMessage = String.format(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.CRITICAL,
                methodName,
                message);
            System.out.print(logMessage);
            fileLoggingHandler(logMessage);
        }
    }
}