package Request;

import Model.AuthToken;

/**
 * A class for event requests.
 */
public class EventRequest extends AuthToken {

    private final String eventID;

    /**
     * Constructor for EventRequest class.
     * @param token A unique authentication token.
     * @param eventID
     */
    public EventRequest(String token, String eventID) {
        super(token);
        this.eventID = eventID;
    }

    public String getEventID() {
        return eventID;
    }
}
