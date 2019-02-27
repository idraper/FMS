package DAO;

import Model.AuthToken;
import Model.User;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * An Authentication Token Class for Data Access.
 */
public class AuthTokenDao extends IDao{

    public static void main (String[] args) {
        AuthTokenDao authTokenDao = new AuthTokenDao();
        authTokenDao.clearTable();
    }

    /**
     * Authentication Token Data Access Constructor.
     */
    public AuthTokenDao() {
        super();
        createTable();
    }

    public void createTable() {
        try {
            SQLHandler.execute_no_rtn("CREATE TABLE IF NOT EXISTS AuthTokens (userName VARCHAR(50) NOT NULL, token VARCHAR(50) NOT NULL, timeExpires DATETIME NOT NULL);");
        } catch (SQLException e) {}
    }

    public boolean clearTable() {
        try {
            SQLHandler.execute_no_rtn("DELETE FROM AuthTokens");
            SQLHandler.execute_no_rtn("VACUUM");
        } catch (SQLException e) {
            return false;
        }
        createTable();
        return true;
    }

    public void removeOld() {
        java.util.Date date = new java.util.Date();
        String cmd = String.format("DELETE FROM AuthTokens WHERE timeExpires < '%s';", new Timestamp(date.getTime()));
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an ArrayList of valid authentication token to send to the client.
     * @param user A user to get their authentication tokens.
     * @return ArrayList of Strings - Authentication tokens associated with the passed user.
     */
    public ArrayList<AuthToken> getAuthTokens(User user) {
        removeOld();
        String cmd = String.format("SELECT * FROM AuthTokens WHERE userName = '%s';", user.getUserName());
        ArrayList<AuthToken> tokens = new ArrayList<>();
        try {
            tokens = toArrayList(SQLHandler.execute(cmd));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    public AuthToken getMostRecentToken(User user) {
        ArrayList<AuthToken> tokens = getAuthTokens(user);
        if (tokens.size() == 0) return null;
        AuthToken bestToken = tokens.get(0);
        for (int i = 1; i < tokens.size(); i++) {
            if (tokens.get(i).compareTo(bestToken) > 0)
                bestToken = tokens.get(i);
        }
        return bestToken;
    }

    public String getUserName(AuthToken token) {
        String cmd = String.format("SELECT * FROM AuthTokens WHERE token = '%s';", token.getToken());
        String userName = null;
        try {
            userName = (String) toObject(SQLHandler.execute(cmd));
        } catch (SQLException e) {}
        return userName;
    }

    /**
     * Creates a new authentication token and adds it to the data storage
     * @param userName A user to add an authentication token for.
     * @return The token that was created.
     */
    public String addAuthToken(String userName) {
        removeOld();
        String token = UUID.randomUUID().toString();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date date = new Date((new java.util.Date()).getTime() + (60 * ONE_MINUTE_IN_MILLIS));
        String cmd = String.format("INSERT INTO AuthTokens VALUES ('%s', '%s', '%s');", userName, token, new Timestamp(date.getTime()));
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {
            return null;
        }
        return token;
    }

    @Override
    public Object toObject(ArrayList<HashMap<String,Object>> data) {
        if (data == null || data.size() == 0) return null;
        return data.get(0).get("userName");
    }

    // returns an arraylist of valid token for the user
    public ArrayList<AuthToken> toArrayList(ArrayList<HashMap<String,Object>> data) {
        ArrayList<AuthToken> rtn = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            rtn.add(new AuthToken((String) data.get(i).get("token"), (String) data.get(i).get("timeExpires")));
        }

        return rtn;
    }
}
