package Service;

import DAO.EventDao;
import DAO.PersonDao;
import DAO.SQLHandler;
import DAO.UserDao;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.LoadResult;
import Result.MsgResult;

/**
 * Service to clear all data from the database (just like ClearService), and then load the posted user, person, and event data into the database.
 */
public class LoadService {

    /**
     * Constructor for LoadService class.
     */
    public LoadService() {
    }

    /**
     * Clears all data from the database (just like ClearService.clear), and then loads the posted user, person, and event data into the database.
     * @param loadRequest A LoadRequest object from a request.
     * @return A LoadResult object after completing tasks.
     */
    public MsgResult load(LoadRequest loadRequest) {
        SQLHandler.connect();
        try {
            ClearService clearService = new ClearService();
            clearService.clear();

            PersonDao personDao = new PersonDao();
            EventDao eventDao = new EventDao();
            UserDao userDao = new UserDao();

            boolean clearP = true;
            boolean clearE = true;
            boolean clearU = true;
            for (Person person : loadRequest.getPersons()) if (!personDao.addPerson(person)) clearP = false;
            for (Event event : loadRequest.getEvents()) if (!eventDao.addEvent(event)) clearE = false;
            for (User user : loadRequest.getUsers()) if (!userDao.addUser(user)) clearU = false;

            if (!clearP)
                return new MsgResult("Failed to add all people.");
            if (!clearE)
                return new MsgResult("Failed to add all events.");
            if (!clearU)
                return new MsgResult("Failed to add all users.");

            return new LoadResult("Successfully added " + loadRequest.getUsers().length + " users, " + loadRequest.getPersons().length + " persons, and " + loadRequest.getEvents().length + " events to the database.");
        }
        finally {
            SQLHandler.close();
        }
    }
}
