package Service;

import DAO.AuthTokenDao;
import DAO.PersonDao;
import DAO.SQLHandler;
import DAO.UserDao;
import Model.Person;
import Model.User;
import Request.PersonRequest;
import Result.MsgResult;
import Result.PersonResult;

import java.util.ArrayList;

/**
 * Service to return Person objects based on the current user. The current user is determined from the provided auth token from the request.
 */
public class PersonService {

    /**
     * Constructor for PersonService class.
     */
    public PersonService() {
    }

    /**
     * Returns the single Person object with the specified ID.
     * @return A PersonResult object after completing tasks.
     */
    public MsgResult person(PersonRequest personRequest) {
        SQLHandler.connect();
        try {
            AuthTokenDao authTokenDao = new AuthTokenDao();
            PersonDao personDao = new PersonDao();
            UserDao userDao = new UserDao();

            String userName = authTokenDao.getUserName(personRequest);

            if (userName == null) return new MsgResult("User authentication failed, are you logged in?");

            User user = userDao.getUser(userName);

            if (user == null) return new MsgResult("User not found");

            Person person = personDao.getPerson(personRequest.getPersonID());

            if (person == null) return new MsgResult("Person not found for user");
            if (!person.getDescendant().equals(user.getUserName()))
                return new MsgResult("Person not associated with this user - access denied.");

            return new PersonResult(person.getDescendant(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFather(), person.getMother(), person.getSpouse());
        }
        finally {
            SQLHandler.close();
        }
    }

    /**
     * Returns ALL family members of the current user. The current user is determined from the provided auth token from the request.
     * @param personRequest A PersonRequest object from a request.
     * @return A PersonResult object after completing tasks.
     */
    public MsgResult people(PersonRequest personRequest) {
        AuthTokenDao authTokenDao = new AuthTokenDao();
        PersonDao personDao = new PersonDao();
        UserDao userDao = new UserDao();

        String userName = authTokenDao.getUserName(personRequest);

        if (userName == null) return new MsgResult("User authentication failed, are you logged in?");

        User user = userDao.getUser(userName);

        if (user == null) return new MsgResult("User not found");

        ArrayList<Person> people = personDao.getPeople(user);

        return new PersonResult(people);
    }
}
