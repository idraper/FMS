package DAO;

import Model.Event;
import Model.Person;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An Event Class for Data Access.
 */
public class EventDao extends IDao{

    /**
     * Event Data Access Constructor.
     */
    public EventDao() {
        super();
        createTable();
    }

    @Override
    public void createTable() {
        String cmd = " CREATE TABLE IF NOT EXISTS Events (" +
                "ID_         VARCHAR(50)    NOT NULL, " +
                "descendant  VARCHAR(50)    NOT NULL, " +
                "person      VARCHAR(50)    NOT NULL, " +
                "latitude    FLOAT           NOT NULL, " +
                "longitude   FLOAT           NOT NULL, " +
                "country     VARCHAR(50)    NOT NULL, " +
                "city        VARCHAR(50)    NOT NULL, " +
                "eventType   VARCHAR(50)    NOT NULL, " +
                "year_       INT             NOT NULL, " +
                "UNIQUE(ID_));";

        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {}
    }

    public boolean clearTable() {
        try {
            SQLHandler.execute_no_rtn("DELETE FROM Events");
            SQLHandler.execute_no_rtn("VACUUM");
        } catch (SQLException e) {
            return false;
        }
        createTable();
        return true;
    }

    public void removeUser(User user) {
        String cmd = String.format("DELETE FROM Events WHERE descendant = '%s';", user.getUserName());
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {}
    }

    @Override
    public Object toObject(ArrayList<HashMap<String, Object>> data) {
        if (data == null || data.size() == 0) return null;
        return new Event(   (String) data.get(0).get("ID_"),
                            (String) data.get(0).get("descendant"),
                            (String) data.get(0).get("person"),
                            (Float) Float.parseFloat((String) data.get(0).get("latitude")),
                            (Float) Float.parseFloat((String) data.get(0).get("longitude")),
                            (String) data.get(0).get("country"),
                            (String) data.get(0).get("city"),
                            (String) data.get(0).get("eventType"),
                            (Integer) Integer.parseInt((String) data.get(0).get("year_")));
        }

    /**
     * Adds an event for a user.
     * @param event The event to add to the database.
     */
    public boolean addEvent(Event event) {
        String cmd = String.format("INSERT INTO Events VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", event.getEventID(), event.getDescendant(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear());
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean addEvent(String eventID, String descendant, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        String cmd = String.format("INSERT INTO Events VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", eventID, descendant, personID, latitude, longitude, country, city, eventType, year);
        try {
            SQLHandler.execute_no_rtn(cmd);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Event getEvent(String eventID) {
        String cmd = String.format("SELECT * FROM Events WHERE ID_ = '%s';", eventID);
        Event event = null;
        try {
            event = (Event) toObject(SQLHandler.execute(cmd));
        }
        catch (SQLException e) {}
        return event;
    }

    /**
     * Gets all events for a user.
     * @param user A user to get all events associated with that user.
     * @return An array of Event objects for the user passed as an argument.
     */
    public ArrayList<Event> getEvents(User user) {
        String cmd = String.format("SELECT * FROM Events WHERE descendant = '%s';", user.getUserName());
        ArrayList<Event> events = new ArrayList<>();
        try {
            events = toArrayList(SQLHandler.execute(cmd));
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return events;
    }

    /**
     * Gets all events for a event.
     * @param person A person to get all events associated with that user.
     * @return An array of Event objects for the person passed as an argument.
     */
    public ArrayList<Event> getEvents(Person person) {
        String cmd = String.format("SELECT * FROM Events WHERE person = '%s';", person.getPersonID());
        ArrayList<Event> events = new ArrayList<>();
        try {
            events = toArrayList(SQLHandler.execute(cmd));
        } catch (IndexOutOfBoundsException e) {}
        catch (SQLException e) {}
        return events;
    }

    private ArrayList<Event> toArrayList(ArrayList<HashMap<String,Object>> data) {
        ArrayList<Event> rtn = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            rtn.add(new Event(  (String) data.get(i).get("ID_"),
                                (String) data.get(i).get("descendant"),
                                (String) data.get(i).get("person"),
                                (Float) Float.parseFloat((String) data.get(0).get("latitude")),
                                (Float) Float.parseFloat((String) data.get(0).get("longitude")),
                                (String) data.get(i).get("country"),
                                (String) data.get(i).get("city"),
                                (String) data.get(i).get("eventType"),
                                (Integer) Integer.parseInt((String) data.get(0).get("year_"))));
        }

        return rtn;
    }

}
