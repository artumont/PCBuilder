import os
import json
import socket
import logging
from typing import Any, Optional

logger = logging.getLogger(__name__)

class DatabaseSocketClient:
    """
    @description: Client for connecting to the database socket server
    """
    def __init__(self, host: str = "localhost", port: int = 9854):
        self.host = host
        self.port = port
        self.socket: Optional[socket.socket] = None
        
    def connect(self) -> bool:
        """
        @description: Establish connection to database socket server
        @return: True if connection successful, False otherwise
        """
        try:
            self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.socket.connect((self.host, self.port))
            logger.info(f"Connected to database server at {self.host}:{self.port}")
            return True
        except Exception as e:
            logger.error(f"Failed to connect to database server: {str(e)}")
            return False

    def disconnect(self) -> None:
        """
        @description: Close the socket connection
        """
        if self.socket:
            self.socket.close()
            self.socket = None
            logger.info("Disconnected from database server")

    def send_request(self, action: str, data: dict) -> Any:
        """
        @description: Send request to database server and get response
        @param action: Type of action to perform (e.g., 'query', 'insert')
        @param data: Data payload for the request
        @return: Server response
        """
        if not self.socket:
            if not self.connect():
                raise ConnectionError("Not connected to database server")

        try:
            # @note: Format request
            request = " ".join([action, json.dumps(data)])
            
            # @note: Send request
            self.socket.sendall(request.encode('utf-8'))
            
            # @note: Get response
            response = self.socket.recv(4096).decode('utf-8')
            return json.loads(response)
            
        except Exception as e:
            logger.error(f"Error during socket communication: {str(e)}")
            self.disconnect()
            raise

# @note: Create singleton instance
db_socket = DatabaseSocketClient(
    host=os.getenv("DB_SOCKET_HOST", "localhost"),
    port=int(os.getenv("DB_SOCKET_PORT", 9854))
)