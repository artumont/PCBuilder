package com.pcbuilder.utilities;

import java.io.*;
import java.net.*;

import com.pcbuilder.helpers.Logger;

public class Sockets {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static Logger logger;
    private volatile boolean isListening = false;
    private static Resolver resolver;
    
    public Sockets(Logger givenLogger, Resolver givenResolver) {
        logger = givenLogger;
        resolver = givenResolver;
    }

    public boolean start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            return true;
        }
        catch (IOException e) {
            logger.error("Sockets.start", String.format("Error starting server: %s", e.getMessage()));
            return false;
        }
    }

    public void startListening() {
        isListening = true;
        logger.info("Sockets.startListening", "Listening for incoming connections");
        Thread listenThread = new Thread(() -> {
            while (isListening) {
                try {
                    clientSocket = serverSocket.accept();
                    logger.info("Sockets.startListening", String.format("New client connected from %s", clientSocket.getInetAddress()));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    while (isListening && clientSocket.isConnected()) {
                        String message = in.readLine();
                        if (message != null) {
                            String[] messageParts = message.split(" ");
                            if (messageParts.length == 2) {
                                try {
                                    String operation = messageParts[0];
                                    String args = messageParts[1];
                                    String result = resolver.resolveOperation(operation, args);
                                    logger.info("Sockets.startListening", String.format("Operation '%s' resolved with args '%s'", operation, args));
                                    out.println(result);
                                }
                                catch (IllegalArgumentException e) {
                                    logger.warning("Sockets.startListening", String.format("Invalid operation: %s", messageParts[0]));
                                    out.println("Invalid operation");
                                    continue;
                                }
                            }
                            else {
                                logger.warning("Sockets.startListening", String.format("Invalid message format: %s", message));
                                out.println("Invalid message format");
                            }
                        }
                    }
                    logger.warning("Sockets.startListening", "Client disconnected or connection lost. Performing cleanup.");
                    cleanup();
                } catch (IOException e) {
                    logger.error("Sockets.startListening", String.format("Connection error: %s", e.getMessage()));
                    cleanup();
                }
            }
        });
        listenThread.start();
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
            logger.error("Sockets.stop", 
                String.format("Error during shutdown: %s", e.getMessage()));
            return false;
        }
    }

    private void cleanup() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            logger.error("Sockets.cleanup", 
                String.format("Error during cleanup: %s", e.getMessage()));
        }
    }
}
