package Request;

/**
 * A class for fill requests.
 */
public class FillRequest {

    /**
     * The userName to fill the database with.
     */
    private final String userName;

    /**
     * The number of generations to create.
     */
    private final int generations;

    /**
     * Constructor for FillRequest class.
     * @param userName The username to fill the database with.
     * @param generations The number of generations to create.
     */
    public FillRequest(String userName, int generations) {
        this.userName = userName;
        this.generations = generations;
    }

    /**
     * Constructor for FillRequest class.
     * @param userName The username to fill the database with.
     */
    public FillRequest(String userName) {
        this.userName = userName;
        this.generations = 4;
    }

    public String getUserName() {
        return userName;
    }

    public int getGenerations() {
        return generations;
    }
}
