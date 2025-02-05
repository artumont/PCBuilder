package com.pcbuilder.helpers;

public class Logger {
    private LogLevel minimumLevel;
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

    public void setLevel(LogLevel level) {
        minimumLevel = level;
    }

    public LogLevel getLevel() {
        return minimumLevel;
    }

    public void debug(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.DEBUG.getSeverity()) {
            System.out.printf(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.DEBUG,
                methodName,
                message);
        }
    }

    public void info(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.INFO.getSeverity()) {
            System.out.printf(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.INFO,
                methodName,
                message);
        }
    }

    public void warning(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.WARNING.getSeverity()) {
            System.out.printf(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.WARNING,
                methodName,
                message);
        }
    }

    public void error(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.ERROR.getSeverity()) {
            System.out.printf(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.ERROR,
                methodName,
                message);
        }
    }
    
    public void critical(String methodName, String message) {
        if (minimumLevel.getSeverity() <= LogLevel.CRITICAL.getSeverity()) {
            System.out.printf(LOG_FORMAT, 
                java.time.LocalDateTime.now(),
                LogLevel.CRITICAL,
                methodName,
                message);
        }
    }
}
