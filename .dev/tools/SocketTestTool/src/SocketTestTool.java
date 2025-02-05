import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class SocketTestTool {
    private JFrame frame;
    private JTextField hostField;
    private JTextField portField;
    private JTextField messageField;
    private JTextArea outputArea;
    private JButton connectButton;
    private JButton sendButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean isConnected = false;

    private void initializeGUI() {
        frame = new JFrame("Socket Test Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Connection panel
        JPanel connectionPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        connectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        hostField = new JTextField("localhost");
        portField = new JTextField("9854");
        connectionPanel.add(new JLabel("Host:"));
        connectionPanel.add(hostField);
        connectionPanel.add(new JLabel("Port:"));
        connectionPanel.add(portField);

        // Message panel
        JPanel messagePanel = new JPanel(new BorderLayout(5, 5));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageField = new JTextField();
        sendButton = new JButton("Send");
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);
        sendButton.setEnabled(false);

        // Output area
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Connect button
        connectButton = new JButton("Connect");
        
        // Action listeners
        connectButton.addActionListener(e -> handleConnection());
        sendButton.addActionListener(e -> sendMessage());

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(connectionPanel, BorderLayout.NORTH);
        topPanel.add(messagePanel, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(connectButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleConnection() {
        if (!isConnected) {
            connect();
        } else {
            disconnect();
        }
    }

    private void connect() {
        new Thread(() -> {
            try {
                String host = hostField.getText();
                int port = Integer.parseInt(portField.getText());
                
                outputArea.append("Attempting to connect to " + host + ":" + port + "\n");
                socket = new Socket(host, port);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                isConnected = true;
                SwingUtilities.invokeLater(() -> {
                    connectButton.setText("Disconnect");
                    sendButton.setEnabled(true);
                    hostField.setEnabled(false);
                    portField.setEnabled(false);
                });
                
                outputArea.append("Connected successfully!\n");
                
                // Start message listener
                startMessageListener();
                
            } catch (Exception e) {
                outputArea.append("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }

    private void disconnect() {
        try {
            isConnected = false;
            if (socket != null) socket.close();
            if (out != null) out.close();
            if (in != null) in.close();
            
            SwingUtilities.invokeLater(() -> {
                connectButton.setText("Connect");
                sendButton.setEnabled(false);
                hostField.setEnabled(true);
                portField.setEnabled(true);
            });
            
            outputArea.append("Disconnected\n");
        } catch (IOException e) {
            outputArea.append("Error disconnecting: " + e.getMessage() + "\n");
        }
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty() && isConnected) {
            out.println(message);
            outputArea.append("Sent: " + message + "\n");
            messageField.setText("");
        }
    }

    private void startMessageListener() {
        new Thread(() -> {
            try {
                String message;
                while (isConnected && (message = in.readLine()) != null) {
                    final String receivedMessage = message;
                    SwingUtilities.invokeLater(() -> 
                        outputArea.append("Received: " + receivedMessage + "\n")
                    );
                }
            } catch (IOException e) {
                if (isConnected) {
                    outputArea.append("Error reading: " + e.getMessage() + "\n");
                    disconnect();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SocketTestTool tool = new SocketTestTool();
            tool.initializeGUI();
        });
    }
}
