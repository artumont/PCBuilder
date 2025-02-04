import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private static Logger logger;

    Database(Logger givenLogger) {
        logger = givenLogger;
    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;integratedSecurity=true;trustServerCertificate=true");
            return true;
        } catch (SQLException e) {
            logger.error("Database.connect", "Error connecting to database: " + e.getMessage());
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("DatabaseConnection.close", "Error closing database connection: " + e.getMessage());
        }
    }
}
