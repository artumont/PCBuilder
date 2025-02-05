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
    
    public Sockets(Logger givenLogger) {
        logger = givenLogger;
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
                            //@todo Implement message processing (resolver)
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
