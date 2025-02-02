import java.util.Hashtable;
import java.util.concurrent.Callable;

public class App {
    public static void main(String[] args) throws Exception {
        Logger logger = new Logger();
        
        if (Boolean.getBoolean("debug.enabled")) {
            logger.setLevel(Logger.LogLevel.DEBUG);
        } else {
            logger.setLevel(Logger.LogLevel.INFO);
        }

        Sockets server = new Sockets(logger, getOperationMap());
        logger.info("Starting server on port 9854");
        
        if (server.start(9854)) {
            logger.info("Server started successfully");
            server.startListening();
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutting down server");
                server.stop();
            }));
        } else {
            logger.error("Failed to start server");
            System.exit(1);
        }
    }

    private static Hashtable<String, Callable<String>> getOperationMap() {
        Hashtable<String, Callable<String>> operations = new Hashtable<>();
        
        return operations;
    }
}
