package tutorial01message;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver {
    // Creates a Logger for this class
    private static final Logger logger = Logger.getLogger(Receiver.class.getName());
    
    // Method to receive and process a message (server handling client request)
    public void receiveMessage(Message message) {
        try {
            // Logs that a message was received
            logger.info("Message received!");
            
            // Validation: checks if the received message is null
            if (message == null) {
                logger.warning("Received null message");
                throw new IllegalArgumentException("Cannot process null message");
            }
            
            // Gets the content from the message
            String content = message.getContent();
            // Logs the content of the received message
            logger.info("Message content: " + content);
        } catch (Exception e) {
            // Catches and logs any exceptions during processing
            logger.log(Level.SEVERE, "Error processing received message", e);
            throw e;
        }
    }
}