package Result;

import Model.AuthToken;

/**
 * A class for register results.
 */
public class RegisterResult extends MsgResult {

    /**
     * Non-empty auth token string.
     */
    private final String authToken;

    /**
     *  User name passed in with request.
     */
    private final String userName;

    /**
     * Non-empty string containing the Person ID of the user's generated Person object.
     */
    private final String personID;

    /**
     * Constructor for RegisterResult class.
     * @param authToken Non-empty auth token string.
     * @param userName User name passed in with request.
     * @param personID Non-empty string containing the Person ID of the user's generated Person object.
     */
    public RegisterResult(String authToken, String userName, String personID) {
        super();
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
}
