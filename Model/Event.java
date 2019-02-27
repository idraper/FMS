package Model;

/**
 * A class to hold the data for an Event.
 */
public class Event {
    /**
     * Unique identifier for this event (non-empty string).
     */
    private final String eventID;

    /**
     * User (Username) to which this person belongs.
     */
    private final String descendant;

    /**
     * eventID of person to which this event belongs.
     */
    private final String person;

    /**
     * Latitude of event's location.
     */
    private final double latitude;

    /**
     * Longitude of event's location.
     */
    private final double longitude;

    /**
     * Country in which event occurred.
     */
    private final String country;

    /**
     * City in which event occurred.
     */
    private final String city;

    /**
     * Type of event (birth, baptism, christening, marriage, death, etc.).
     */
    private final String eventType;

    /**
     * Year in which event occurred.
     */
    private final int year;

    /**
     * Constructor for the Event class.
     * @param eventID Unique identifier for this event (non-empty string).
     * @param descendant User (Username) to which this person belongs.
     * @param personID eventID of person to which this event belongs.
     * @param latitude Latitude of event's location.
     * @param longitude Longitude of event's location.
     * @param country Country in which event occurred.
     * @param city City in which event occurred.
     * @param eventType Type of event (birth, baptism, christening, marriage, death, etc.).
     * @param year Year in which event occurred.
     */
    public Event(String eventID, String descendant, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.person = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getPersonID() {
        return person;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEventType() {
        return eventType;
    }

    public int getYear() {
        return year;
    }
}
