package DAO;

import Model.Event;
import Model.Person;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A User Class for Data Access.
 */
public class UserDao extends IDao {

    /**
     * User Data Access Constructor.
     */
    public UserDao() {
        super();
        createTable();
    }

    /**
     * Creates the Users table in the database.
     */
    @Override
    public void createTable() {
        String cmd = "CREATE TABLE IF NOT EXISTS Users (" +
                "userName    VARCHAR(50)    NOT NULL, " +
                "password    VARCHAR(50)    NOT NULL, " +
                "email       VARCHAR(50)    NOT NULL, " +
                "firstName   VARCHAR(50)    NOT NULL, " +
                "lastName    VARCHAR(50)    NOT NULL, " +
                "gender      CHAR            NOT NULL, " +
                "personID    VARCHAR(50)    NOT NULL, " +
                "UNIQUE(userName), " +
                "UNIQUE(email), " +
                "UNIQUE(personID));";
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {}
    }

    /**
     * Removes and re-creates the Users table from the database.
     */
    @Override
    public boolean clearTable() {
        try {
            SQLHandler.execute_no_rtn("DELETE FROM Users");
            SQLHandler.execute_no_rtn("VACUUM");
        } catch (SQLException e) {
            return false;
        }
        createTable();
        return true;
    }

    /**
     * Add a user to the database.
     * @param user A User object to add to the database.
     * @return True if User was added, false if not.
     */
    public boolean addUser(User user) {
        String cmd = String.format("INSERT INTO Users VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');", user.getUserName(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender(), user.getPersonID());
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Gets the User object associated with a person (may be null).
     * @param person The Person object to get the User object from the database.
     * @return The User object associated with the person passed (may be null).
     */
    public User getUser(Person person) {
        String cmd = String.format("SELECT * FROM Users WHERE personID = '%s';", person.getPersonID());
        User user = null;
        try {
            user = (User) toObject(SQLHandler.execute(cmd));
            return user;
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return user;
    }

    /**
     * Gets all people associated with an event.
     * @param event The Event object to get the Person objects from the database.
     * @return An array of Person objects associated with the event passed.
     */
    public User getUser(Event event) {

        return null;
    }

    /**
     * Gets the User object associated with a userName.
     * @param userName The user to find.
     * @return The associated User object.
     */
    public User getUser(String userName) {
        String cmd = String.format("SELECT * FROM Users WHERE userName = '%s';", userName);
        User user = null;
        try {
            user = (User) toObject(SQLHandler.execute(cmd));
            return user;
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return user;
    }

    /**
     * From a HashMap it return a User object (from database)
     * @param data An ArrayList of HashMaps, where each HashMap is the data for a row return from the database.
     * @return A User object, null if no entries were found in the database.
     */
    @Override
    public Object toObject(ArrayList<HashMap<String,Object>> data) {
        if (data == null) return null;
        return new User((String) data.get(0).get("userName"),
                        (String) data.get(0).get("password"),
                        (String) data.get(0).get("email"),
                        (String) data.get(0).get("firstName"),
                        (String) data.get(0).get("lastName"),
                        (String) data.get(0).get("gender"),
                        (String) data.get(0).get("personID"));
    }
}
