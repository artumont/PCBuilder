import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.concurrent.Callable;

public class Sockets {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static Logger logger;
    private volatile boolean isListening = false;
    private static Hashtable<String, Callable<String>> operationMapping;

    /**
     * @method Sockets.getInstance(Logger logger, Hashtable<String, Callable<String>> operationMapping)
     * @desc Returns a new Sockets instance with the specified logger and operation mapping.
     * @func Constructs a new Sockets instance with the specified logger and operation mapping.
     * @param givenLogger The logger instance to use for logging events and errors
     * @param givenOperationMapping A hashtable mapping operation strings to their corresponding callable implementations
     */
    Sockets(Logger givenLogger, Hashtable<String, Callable<String>> givenOperationMapping) {
        logger = givenLogger;
        operationMapping = givenOperationMapping;
    }

    /**
     * @method Sockets.start(int port)
     * @desc Initializes the server socket on the specified port.
     * @param port The port number to bind the server socket to
     * @return true if the server socket was successfully created, false otherwise
     */
    public boolean start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            return true;
        }
        catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * @method Sockets.startListening()
     * @desc Starts listening for incoming client connections in a separate thread.
     * @func incoming messages by matching them against the operation mapping and executes the corresponding callable function.
     */
    public void startListening() {
        isListening = true;
        logger.info("Listening for incoming connections");
        
        Thread listenThread = new Thread(() -> {
            while (isListening) {
                try {
                    clientSocket = serverSocket.accept();
                    logger.info("New client connected");
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    while (isListening && clientSocket.isConnected()) {
                        String message = in.readLine();
                        if (message != null) {
                            logger.info("Received message: " + message);
                            String[] messageParts = message.split(" ");
                            String operation = messageParts[0];
                            if (operationMapping.get(operation) != null) {
                                try {
                                    String response = operationMapping.get(operation).call();
                                    out.println(response);
                                } catch (Exception e) {
                                    logger.error("Error processing operation: " + e.getMessage());
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    logger.error("Connection error: " + e.getMessage());
                    cleanup();
                }
            }
        });
        listenThread.start();
    }

    /**
     * @method Sockets.cleanup()
     * @desc Cleans up resources by closing input/output streams and client socket.
     * @cause Called when a connection error occurs.
     */
    private void cleanup() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            logger.error("Error during cleanup: " + e.getMessage());
        }
    }

    /**
     * @method Sockets.stop()
     * @desc Stops the server and closes all open connections and resources.
     * @return true if all resources were successfully closed, false if an error occurred
     */
    public boolean stop() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            return true;
        }
        catch (IOException e) {
            logger.error("Error during shutdown: " + e.getMessage());
            return false;
        }
    }
}
