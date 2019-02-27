package Result;

import Model.Event;

import java.util.ArrayList;

/**
 * A class for event results.
 */
public class EventResult extends MsgResult {

    /**
     * Array of Event objects (may be null).
     */
    private final ArrayList<Event> data;

    /**
     * Name of user account this event belongs to (non-empty string).
     */
    private final String descendant;

    /**
     * Event's unique ID (non-empty string).
     */
    private final String eventID;

    /**
     * ID of the person this event belongs to (non-empty string).
     */
    private final String personID;

    /**
     * Latitude of the event's location (number).
     */
    private final Double latitude;

    /**
     * Longitude of the event's location (number).
     */
    private final Double longitude;

    /**
     * Name of country where event occurred (non-empty string).
     */
    private final String country;

    /**
     * Name of city where event occurred (non-empty string).
     */
    private final String city;

    /**
     * Type of event ("birth", "baptism", etc.) (non-empty string).
     */
    private final String eventType;

    /**
     * Year the event occurred (integer).
     */
    private final Integer year;

    /**
     * Constructor for EventResult class representing a single event.
     * @param descendant Name of user account this event belongs to (non-empty string).
     * @param eventID Event's unique ID (non-empty string).
     * @param personID ID of the person this event belongs to (non-empty string).
     * @param latitude Latitude of the event's location (number).
     * @param longitude Longitude of the event's location (number).
     * @param country Name of country where event occurred (non-empty string).
     * @param city Name of city where event occurred (non-empty string).
     * @param eventType Type of event ("birth", "baptism", etc.) (non-empty string).
     * @param year Year the event occurred (integer).
     */
    public EventResult(String descendant, String eventID, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        super();
        this.data = null;
        this.descendant = descendant;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * Constructor for EventResult class representing multiple events.
     * @param data Array of Event objects (may be null).
     */
    public EventResult(ArrayList<Event> data) {
        super();
        this.data = data;
        this.descendant = null;
        this.eventID = null;
        this.personID = null;
        this.latitude = null;
        this.longitude = null;
        this.country = null;
        this.city = null;
        this.eventType = null;
        this.year = null;
    }
}
