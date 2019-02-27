package Service;

import DAO.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.EventRequest;
import Result.EventResult;
import Result.MsgResult;

import java.util.ArrayList;

/**
 * Service to get events based on the current user. The current user is determined from the provided auth token.
 */
public class EventService {

    /**
     * Constructor for EventService class.
     */
    public EventService() {
    }

    /**
     * Returns the single Event object with the specified ID.
     * @param eventRequest An EventRequest object from a request.
     * @return An EventResult object after completing tasks.
     */
    public MsgResult event(EventRequest eventRequest) {
        AuthTokenDao authTokenDao = new AuthTokenDao();
        EventDao eventDao = new EventDao();
        UserDao userDao = new UserDao();

        String userName = authTokenDao.getUserName(eventRequest);

        if (userName == null) return new MsgResult("User authentication failed, are you logged in?");

        User user = userDao.getUser(userName);

        if (user == null) return new MsgResult("User not found");

        Event event = eventDao.getEvent(eventRequest.getEventID());

        if (event == null) return new MsgResult("Event not found");
        if (!event.getDescendant().equals(user.getUserName())) return new MsgResult("Event not associated with this user - access denied.");

        return new EventResult(event.getDescendant(), event.getEventID(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear());
    }

    /**
     * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
     * @param eventRequest An EventRequest object from a request.
     * @return An EventResult object after completing tasks.
     */
    public MsgResult events(EventRequest eventRequest) {
        SQLHandler.connect();
        try {
            AuthTokenDao authTokenDao = new AuthTokenDao();
            EventDao eventDao = new EventDao();
            UserDao userDao = new UserDao();

            String userName = authTokenDao.getUserName(eventRequest);

            if (userName == null) return new MsgResult("User authentication failed, are you logged in?");

            User user = userDao.getUser(userName);

            if (user == null) return new MsgResult("User not found");

            ArrayList<Event> events = eventDao.getEvents(user);

            return new EventResult(events);
        }
        finally {
            SQLHandler.close();
        }
    }

}
