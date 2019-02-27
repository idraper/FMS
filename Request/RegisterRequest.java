package Request;

/**
 * A class for register requests.
 */
public class RegisterRequest {

    /**
     * Unique user name (non-empty string).
     */
    private final String userName;

    /**
     * User's password (non-empty string).
     */
    private final String password;

    /**
     * User's email address (non-empty string).
     */
    private final String email;

    /**
     *  User's first name (non-empty string).
     */
    private final String firstName;

    /**
     * User's last name (non-empty string).
     */
    private final String lastName;

    /**
     * User's gender (string: "f" or "m").
     */
    private final String gender;

    /**
     * Constructor for RegisterRequest class.
     * @param userName Unique user name (non-empty string).
     * @param password User's password (non-empty string).
     * @param email User's email address (non-empty string).
     * @param firstName Users's first name (non-empty string).
     * @param lastName User's last name (non-empty string).
     * @param gender User's gender (string: "f" or "m").
     */
    public RegisterRequest(String userName, String password, String email, String firstName, String lastName, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }
}
