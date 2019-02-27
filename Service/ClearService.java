package Service;

import DAO.*;
import Result.MsgResult;

/**
 * Service to delete ALL data from the database, including user accounts, auth tokens, and generated person and event data.
 */
public class ClearService {

    /**
     * Constructor for ClearService class.
     */
    public ClearService() {
    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data.
     * @return A MsgResult object after completing tasks.
     */
    public MsgResult clear() {
        SQLHandler.connect();
        try {
            AuthTokenDao authTokenDao = new AuthTokenDao();
            PersonDao personDao = new PersonDao();
            EventDao eventDao = new EventDao();
            UserDao userDao = new UserDao();

            boolean clearA = authTokenDao.clearTable();
            boolean clearP = personDao.clearTable();
            boolean clearE = eventDao.clearTable();
            boolean clearU = userDao.clearTable();

            if (clearA && clearP && clearU)
                return new MsgResult("Clear succeeded.");

            if (!clearA)
                return new MsgResult("Failed to clear authentication table.");
            if (!clearP)
                return new MsgResult("Failed to clear persons table.");
            if (!clearE)
                return new MsgResult("Failed to clear events table.");
            if (!clearU)
                return new MsgResult("Failed to clear users table.");

            return new MsgResult("Unknown error.");
        }
        finally {
            SQLHandler.execute_batch();
            SQLHandler.close();
        }
    }

}
