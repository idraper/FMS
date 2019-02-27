package Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * An abstract class to hold the data for an authentication token.
 */
public class AuthToken implements Comparable<AuthToken> {

    /**
     * An authentication token.
     */
    private final String token;

    /**
     * A datetime object for when this token expires.
     */
    private final Date timeExpires;

    /**
     * Constructor for the AuthToken class.
     * @param token The token to create.
     * @param timeExpires
     */
    public AuthToken(String token, Date timeExpires) {
        this.token = token;
        this.timeExpires = timeExpires;
    }

    public AuthToken(String token, String timeStr) {
        this.token = token;
        Date tmp = null;
        try {
            tmp = new Date((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(timeStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.timeExpires = tmp;
    }

    public AuthToken(String token) {
        this.token = token;
        this.timeExpires = null;
    }

    public String getToken() {
        return token;
    }

    @Override
    public int compareTo(AuthToken o) {
        return this.timeExpires.compareTo(o.timeExpires);
    }
}
