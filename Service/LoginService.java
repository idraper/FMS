package Service;

import DAO.AuthTokenDao;
import DAO.SQLHandler;
import DAO.UserDao;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;
import Result.MsgResult;

/**
 * Service to check login credentials with the database and if correct, log in the user.
 */
public class LoginService {

    /**
     * Constructor for LoginService class.
     */
    public LoginService() {
    }

    /**
     * Checks login credentials with the database and if correct, logs in the user and returns an auth token.
     * @param loginRequest A LoginRequest object from a request.
     * @return A Result object after completing tasks (either error or LoginResult).
     */
    public MsgResult login(LoginRequest loginRequest) {
        SQLHandler.connect();
        try {
            AuthTokenDao authTokenDao = new AuthTokenDao();
            UserDao userDao = new UserDao();

            User user = userDao.getUser(loginRequest.getUserName());

            if (user == null) return new MsgResult("User does not exist");

            if (!user.getPassword().equals(loginRequest.getPassword())) return new MsgResult("Password was incorrect");

            authTokenDao.addAuthToken(user.getUserName());

            AuthToken token = authTokenDao.getMostRecentToken(user);
            if (token == null) return new MsgResult("No valid authentication token found, user not logged in");

            return new LoginResult(token.getToken(), user.getUserName(), user.getPersonID());
        }
        finally {
            SQLHandler.close();
        }
    }
}
