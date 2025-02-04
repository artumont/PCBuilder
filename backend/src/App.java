import java.util.Hashtable;
import java.util.concurrent.Callable;

public class App {
    private static Logger logger = new Logger();

    public static void main(String[] args) throws Exception {
        if (Boolean.getBoolean("debug.enabled")) {
            logger.setLevel(Logger.LogLevel.DEBUG);
        } else {
            logger.setLevel(Logger.LogLevel.INFO);
        }

        Sockets server = new Sockets(logger, getOperationMap());
        Database database = new Database(logger);
        
        if (server.start(9854)) {
            logger.info("App.main", "Server started successfully");

            serviceStartupAssurance(database.connect(), "Database");

            server.startListening();
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("App.main", "Shutting down server");
                server.stop();
            }));
        } else {
            logger.error("App.main", "Failed to start server");
            System.exit(1);
        }
    }

    private static Hashtable<String, Callable<String>> getOperationMap() {
        Hashtable<String, Callable<String>> operations = new Hashtable<>();
        
        return operations;
    }

    /**
     * @method serviceStartupAssurance(boolean serviceStatus, String serviceName)
     * @desc Ensures that the specified service has started successfully, exiting the application if not.
     * @func Logs an error message and exits the application if the service failed to start, otherwise logs a success message.
     */
    private static void serviceStartupAssurance(boolean serviceStatus, String serviceName) {
        if (!serviceStatus) {
            logger.error("App.main", "Failed to start service: [" + serviceName + "], shutting down");
            System.exit(1);
        }
        else {
            logger.info("App.main", serviceName + " started successfully");
        }
    }
}
