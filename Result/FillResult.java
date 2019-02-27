package Result;

/**
 * A class for fill results.
 */
public class FillResult extends MsgResult {

    /**
     * Constructor for FillResult class.
     * @param message Message to return as a response.
     */
    public FillResult(String message) {
        super();
        this.message = message;
    }
}
