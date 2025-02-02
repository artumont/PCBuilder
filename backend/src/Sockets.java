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

    Sockets(Logger givenLogger, Hashtable<String, Callable<String>> givenOperationMapping) {
        logger = givenLogger;
        operationMapping = givenOperationMapping;
    }

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

    public void startListening() {
        isListening = true;
        logger.info("Listening for incoming connections");
        
        Thread listenThread = new Thread(() -> {
            while (isListening) {
                try {
                    // Accept new client connections
                    clientSocket = serverSocket.accept();
                    logger.info("New client connected");
                    
                    // Initialize streams
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    
                    // Handle client messages
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

    private void cleanup() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            logger.error("Error during cleanup: " + e.getMessage());
        }
    }

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
