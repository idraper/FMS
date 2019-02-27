package Service;

import DAO.AuthTokenDao;
import DAO.PersonDao;
import DAO.SQLHandler;
import DAO.UserDao;
import Model.Person;
import Model.User;
import Request.RegisterRequest;
import Result.MsgResult;
import Result.RegisterResult;

import java.util.UUID;

/**
 * Service to create a new user account, generate 4 generations of ancestor data for the new user, and log the user in.
 */
public class RegisterService {

    /**
     * Constructor for RegisterService class.
     */
    public RegisterService() {
    }

    /**
     * Creates a new user account, generates 4 generations of ancestor data for the new user, logs the user in, and returns an auth token.
     * @param registerRequest A RegisterRequest object from a request.
     * @return A RegisterResult object after completing tasks.
     */
    public MsgResult register(RegisterRequest registerRequest) {
        SQLHandler.connect();
        try {
            AuthTokenDao authTokenDao = new AuthTokenDao();
            PersonDao personDao = new PersonDao();
            UserDao userDao = new UserDao();

            User newUser = userDao.getUser(registerRequest.getUserName());
            if (newUser != null) return new MsgResult("User already exists");

            String personID = UUID.randomUUID().toString();
            newUser = new User(registerRequest.getUserName(), registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getGender(), personID);
            Person newPerson = new Person(personID, newUser.getUserName(), newUser.getFirstName(), newUser.getLastName(), newUser.getGender(), null, null, null);

            userDao.addUser(newUser);
            personDao.addPerson(newPerson);

            String token = authTokenDao.addAuthToken(registerRequest.getUserName());

            return new RegisterResult(token, newUser.getUserName(), newUser.getPersonID());
        }
        finally {
            SQLHandler.execute_batch();
            SQLHandler.close();
        }
    }
}
