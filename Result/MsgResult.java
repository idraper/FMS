package Result;

/**
 * A class to hold the message response for Result classes.
 */
public class MsgResult {

    /**
     * String to hold error messages to the client.
     */
    protected String message;

    /**
     * Default constructor for error message - begins with no errors (if any occur, child may set custom error message).
     */
    public MsgResult() {
        message = "Success";
    }

    /**
     * Error message constructor.
     */
    public MsgResult(String message) {
        this.message = message;
    }

}
