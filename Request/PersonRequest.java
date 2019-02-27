package Request;

import Model.AuthToken;

/**
 * A class for person requests.
 */
public class PersonRequest extends AuthToken {

    private final String personID;

    /**
     * Constructor for PersonRequest class.
     * @param token A unique authentication token.
     * @param personID
     */
    public PersonRequest(String token, String personID) {
        super(token);
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

}
