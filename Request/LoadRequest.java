package Request;

import Model.Event;
import Model.Person;
import Model.User;

/**
 * A class for load requests.
 */
public class LoadRequest {

    /**
     * Array of User objects.
     */
    private final User[] users;

    /**
     * Array of Person objects.
     */
    private final Person[] persons;

    /**
     * Array of Event objects.
     */
    private final Event[] events;

    /**
     * Constructor for LoadRequest class.
     * @param users Array of User objects.
     * @param persons Array of Person objects.
     * @param events Array of Event objects.
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public Event[] getEvents() {
        return events;
    }
}
