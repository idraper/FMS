package Request;

/**
 * A class for login requests.
 */
public class LoginRequest {

    /**
     * Username for user (non-empty string).
     */
    private final String userName;

    /**
     * Password for user (non-empty string).
     */
    private final String password;

    /**
     * Constructor for LoginRequest class.
     * @param userName Username for user (non-empty string).
     * @param password Password for user (non-empty string).
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
