package tutorial01message;

// Imports for logging configuration
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessagePassingExample {
    // Creates a Logger for this class
    private static final Logger logger = Logger.getLogger(MessagePassingExample.class.getName());
    
    // Main method - entry point of the program
    public static void main(String[] args) {
        try {
            // Configures the logging system
            configureLogger();
            logger.info("Starting Message Passing Example");
            
            // Creates client and server instances
            logger.info("Creating Sender and Receiver instances");
            Sender sender = new Sender();
            Receiver receiver = new Receiver();
            
            // Client (Sender) creates a message
            logger.info("Creating a test message");
            Message message = sender.createMessage("Hello Server! This is a test message.");
            
            // Client sends message to server
            logger.info("Sending message to receiver");
            sender.sendMessage(receiver, message);
            
            // Demonstrates exception handling with a deliberately invalid situation
            try {
                logger.info("Demonstrating exception handling with null message");
                Message nullMessage = null;
                // This will cause an exception because nullMessage is null
                sender.sendMessage(receiver, nullMessage);
            } catch (Exception e) {
                // Catches and logs the expected exception
                logger.log(Level.WARNING, "Caught expected exception from null message test", e);
                // We catch it here because this was a deliberate test
            }
            
            logger.info("Message Passing Example completed successfully");
        } catch (Exception e) {
            // Catches any unhandled exceptions in the main method
            logger.log(Level.SEVERE, "Unhandled exception in main", e);
        }
    }
    
    // Helper method to configure the logging system
    private static void configureLogger() {
        try {
            // Gets the root logger (parent of all loggers)
            Logger globalLogger = Logger.getLogger("");
            Handler[] handlers = globalLogger.getHandlers();
            
            // Removes existing handlers to start fresh
            for (Handler handler : handlers) {
                globalLogger.removeHandler(handler);
            }
            
            // Creates a new console handler for output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            // Sets it to show ALL log levels
            consoleHandler.setLevel(Level.ALL);
            // Adds the handler to the logger
            globalLogger.addHandler(consoleHandler);
            // Sets the global level to ALL
            globalLogger.setLevel(Level.ALL);
            
            logger.info("Logger configured successfully");
        } catch (Exception e) {
            // Handles any errors during logger configuration
            Logger.getGlobal().log(Level.SEVERE, "Error configuring logger: " + e.getMessage(), e);
        }
    }
}