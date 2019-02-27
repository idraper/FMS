package DAO;

import Model.Event;
import Model.Person;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Person Class for Data Access.
 */
public class PersonDao extends IDao{

    /**
     * Person Data Access Constructor.
     */
    public PersonDao() {
        super();
        createTable();
    }

    public void createTable() {
        String cmd = "CREATE TABLE IF NOT EXISTS Persons (" +
                "ID_         VARCHAR(50)    NOT NULL, " +
                "descendant  VARCHAR(50)    NOT NULL, " +
                "firstName   VARCHAR(50)    NOT NULL, " +
                "lastName    VARCHAR(50)    NOT NULL, " +
                "gender      CHAR            NOT NULL, " +
                "father      VARCHAR(50), " +
                "mother      VARCHAR(50), " +
                "spouse      VARCHAR(50), " +
                "UNIQUE(ID_));";

        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {}
    }

    public boolean clearTable() {
        try {
            SQLHandler.execute_no_rtn("DELETE FROM Persons");
            SQLHandler.execute_no_rtn("VACUUM");
        } catch (SQLException e) {
            return false;
        }
        createTable();
        return true;
    }

    public void removeUser(User user) {
        String cmd = String.format("DELETE FROM Persons WHERE descendant = '%s';", user.getUserName());
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {}
    }

    /**
     * Add a person to the database.
     * @param person The Person object to add to the database.
     */
    public boolean addPerson(Person person) {
        String cmd = String.format("INSERT INTO Persons VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", person.getPersonID(), person.getDescendant(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFather(), person.getMother(), person.getSpouse());
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifies the information for an individual in the database.
     * @param person An updated version of the person to change.
     */
    public void modifyPerson(Person person) {

    }

    /**
     * Gets the Person object associated with a user.
     * @param user The user to get the associated Person objects with.
     * @return The Person object for the user passed as an argument.
     */
    public Person getPerson(User user) {
        String cmd = String.format("SELECT * FROM Persons WHERE ID_ = '%s';", user.getPersonID());
        Person person = null;
        try {
            person = (Person) toObject(SQLHandler.execute(cmd));
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return person;
    }

    /**
     * Gets the Person object associated with a user.
     * @param personID The personID to get the associated Person object with.
     * @return The Person object for the user passed as an argument.
     */
    public Person getPerson(String personID) {
        String cmd = String.format("SELECT * FROM Persons WHERE ID_ = '%s';", personID);
        Person person = null;
        try {
            person = (Person) toObject(SQLHandler.execute(cmd));
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return person;
    }

    /**
     * Gets all Person objects associated with a user.
     * @param user The user to get the associated people with.
     * @return An array of Person objects for the user passed as an argument.
     */
    public ArrayList<Person> getPeople(User user) {
        String cmd = String.format("SELECT * FROM Persons WHERE descendant = '%s';", user.getUserName());
        ArrayList<Person> people = new ArrayList<>();
        try {
            people = toArrayList(SQLHandler.execute(cmd));
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return people;
    }

    /**
     * Gets all Person objects associated with an event.
     * @param event The event ot get all associated Person objects with.
     * @return An array of Person objects for the event passed as an argument.
     */
    public ArrayList<Person> getPeople(Event event) {
        return null;
    }

    /**
     * From a HashMap it return a Person object (from database)
     * @param data An ArrayList of HashMaps, where each HashMap is the data for a row return from the database.
     * @return A Person object, null if no entries were found in the database.
     */
    @Override
    public Object toObject(ArrayList<HashMap<String, Object>> data) {
        if (data == null || data.size() == 0) return null;
        return new Person(  (String) data.get(0).get("ID_"),
                            (String) data.get(0).get("descendant"),
                            (String) data.get(0).get("firstName"),
                            (String) data.get(0).get("lastName"),
                            (String) data.get(0).get("gender"),
                            (String) data.get(0).get("father"),
                            (String) data.get(0).get("mother"),
                            (String) data.get(0).get("spouse")
                );
    }

    private ArrayList<Person> toArrayList(ArrayList<HashMap<String,Object>> data) {
        ArrayList<Person> rtn = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            rtn.add(new Person( (String) data.get(i).get("ID_"),
                                (String) data.get(i).get("descendant"),
                                (String) data.get(i).get("firstName"),
                                (String) data.get(i).get("lastName"),
                                (String) data.get(i).get("gender"),
                                (String) data.get(i).get("father"),
                                (String) data.get(i).get("mother"),
                                (String) data.get(i).get("spouse")
            ));
        }

        return rtn;
    }

}
