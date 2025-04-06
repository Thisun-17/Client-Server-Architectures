// Declares which package this class belongs to - packages organize related classes
package tutorial01message;

// Imports the Logger class from Java's logging package
import java.util.logging.Logger;

// Defines a public class named Message - public means other classes can use it
public class Message {
    // Creates a Logger instance for this class; static means it's shared across all instances
    // final means its value can't be changed after initialization
    private static final Logger logger = Logger.getLogger(Message.class.getName());
    
    // Private instance variable to store the message content
    // Private means it's only accessible within this class
    private String content;
    
    // Constructor - special method called when creating a new Message object
    public Message(String content) {
        // Assigns the provided content to the instance variable
        this.content = content;
        // Logs message creation at FINE level (detailed information)
        logger.fine("Message object created with content: " + content);
    }
    
    // Getter method - provides access to the private content variable
    public String getContent() {
        // Logs at FINEST level (very detailed tracing information)
        logger.finest("getContent() called");
        // Returns the content value
        return content;
    }
}