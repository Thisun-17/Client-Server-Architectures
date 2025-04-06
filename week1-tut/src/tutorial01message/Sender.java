package tutorial01message;

// Imports for logging levels and Logger class
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender {
    // Creates a Logger for this class
    private static final Logger logger = Logger.getLogger(Sender.class.getName());
    
    // Method to create a message (client preparing a request)
    public Message createMessage(String content) {
        try {
            // Logs at INFO level (general processing information)
            logger.info("Creating a new message...");
            
            // Validation: checks if content is null and handles the error
            if (content == null) {
                // Logs a warning message
                logger.warning("Attempting to create message with null content");
                // Throws an exception when invalid data is detected
                throw new IllegalArgumentException("Message content cannot be null");
            }
            
            // Creates a new Message object with the given content
            Message message = new Message(content);
            logger.info("Message created: " + content);
            // Returns the created message
            return message;
        } catch (Exception e) {
            // Catches any exceptions that might occur
            // Logs error details at SEVERE level with exception information
            logger.log(Level.SEVERE, "Error creating message", e);
            // Re-throws the exception for the caller to handle
            throw e;
        }
    }
    
    // Method to send a message to a receiver (client sending request to server)
    public void sendMessage(Receiver receiver, Message message) {
        try {
            logger.info("Sending message to receiver...");
            
            // Validation: checks if receiver is null
            if (receiver == null) {
                logger.severe("Receiver is null, cannot send message");
                throw new IllegalArgumentException("Receiver cannot be null");
            }
            
            // Validation: checks if message is null
            if (message == null) {
                logger.severe("Message is null, cannot send null message");
                throw new IllegalArgumentException("Message cannot be null");
            }
            
            // Calls the receiveMessage method on the receiver (server)
            receiver.receiveMessage(message);
            logger.info("Message sent successfully");
        } catch (Exception e) {
            // Catches and logs any exceptions
            logger.log(Level.SEVERE, "Error sending message", e);
            throw e;
        }
    }
}