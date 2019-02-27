package Tests;

import DAO.UserDao;
import DAO.SQLHandler;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * A class to run a series of tests on the UserDao class.
 */
public class UserDaoTests {

    @Test
    public void clearPositiveTest() {
        System.out.println("Running Test: Clear User's Table From Database POSITIVE");
        UserDao userDao = new UserDao();
        userDao.clearTable();

        userDao.addUser(new User("test0","","test0@gmail.com","Isaac","Draper","","0"));
        userDao.addUser(new User("test1","","test1@gmail.com","","","","1"));
        userDao.addUser(new User("test2","","test2@gmail.com","","","","2"));
        userDao.addUser(new User("test3","","test3@gmail.com","","","","3"));
        userDao.addUser(new User("test4","","test4@gmail.com","","","","4"));
        userDao.addUser(new User("test5","","test5@gmail.com","","","","5"));
        userDao.addUser(new User("test6","","test6@gmail.com","","","","6"));
        userDao.addUser(new User("test7","","test7@gmail.com","","","","7"));
        userDao.addUser(new User("test8","","test8@gmail.com","","","","8"));
        userDao.addUser(new User("test9","","test9@gmail.com","","","","9"));

        SQLHandler.connect();
        int numRowsBefore = SQLHandler.getNumRows("Users");

        userDao.clearTable();

        int numRowsAfter = SQLHandler.getNumRows("Users");
        SQLHandler.close();

        System.out.println(String.format("\tNumber of rows before clear: %d\n\tNumber of rows after clear: %d", numRowsBefore, numRowsAfter));
        System.out.println(numRowsAfter == 0 ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertTrue(numRowsBefore > 0);
        Assertions.assertTrue(numRowsAfter == 0);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void clearNegativeTest() {
        System.out.println("Running Test: Clear User's Table From Database NEGATIVE");
        UserDao userDao = new UserDao();
        userDao.clearTable();

        SQLHandler.connect();

        try {
            SQLHandler.execute_no_rtn("DROP TABLE Users");
        } catch (SQLException e) {}

        SQLHandler.close();

        System.out.println(String.format("\tAttempting to clear table when it does not exist (should fail):"));
        boolean cleared = userDao.clearTable();

        System.out.println(cleared ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertFalse(cleared);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void addPositiveTest() {
        System.out.println("Running Test: Add user to Database POSITIVE");
        UserDao userDao = new UserDao();
        userDao.clearTable();

        System.out.println("\tAttempting to add user with userName test0:");
        boolean added = userDao.addUser(new User("test0","","test0@gmail.com","","","","0"));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println("\tAttempting to add user with userName test1:");
        added = userDao.addUser(new User("test1","","test1@gmail.com","","","","1"));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println("\tAttempting to add user with userName test2:");
        added = userDao.addUser(new User("test2","","test2@gmail.com","","","","2"));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void addNegativeTest() {
        System.out.println("Running Test: Add user to Database NEGATIVE");
        UserDao userDao = new UserDao();
        userDao.clearTable();

        System.out.println("\tAttempting to add user with userName test0:");
        boolean added = userDao.addUser(new User("test0","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println("\tAttempting to add user with userName test0:");
        added = userDao.addUser(new User("test0","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertFalse(added);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void getPositiveTest() {
        System.out.println("Running Test: Get user from Database POSITIVE");
        UserDao userDao = new UserDao();
        userDao.clearTable();
        userDao.addUser(new User("test0","","test0@gmail.com","Isaac","Draper","","0"));
        userDao.addUser(new User("test1","","test1@gmail.com","","","","1"));
        userDao.addUser(new User("test2","","test2@gmail.com","","","","2"));
        userDao.addUser(new User("test3","","test3@gmail.com","","","","3"));
        userDao.addUser(new User("test4","","test4@gmail.com","","","","4"));
        userDao.addUser(new User("test5","","test5@gmail.com","","","","5"));
        userDao.addUser(new User("test6","","test6@gmail.com","","","","6"));
        userDao.addUser(new User("test7","","test7@gmail.com","","","","7"));
        userDao.addUser(new User("test8","","test8@gmail.com","","","","8"));
        userDao.addUser(new User("test9","","test9@gmail.com","","","","9"));

        System.out.println("\tAttempting to get user from person with id 0:");
        User user= userDao.getUser(new Person("0","","","","","","",""));

        System.out.println(user != null ? "\tFound user with name: " + user.getFirstName() + " " + user.getLastName() : "\tCould not find user");
        System.out.println(user != null ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getUserName(), "test0");
        Assertions.assertEquals(user.getPersonID(), "0");
        Assertions.assertEquals(user.getEmail(), "test0@gmail.com");

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void getNegativeTest() {
        System.out.println("Running Test: Get user from Database NEGATIVE");
        UserDao userDao = new UserDao();
        userDao.clearTable();

        userDao.addUser(new User("test0","","test0@gmail.com","Isaac","Draper","","0"));
        userDao.addUser(new User("test1","","test1@gmail.com","","","","1"));
        userDao.addUser(new User("test2","","test2@gmail.com","","","","2"));
        userDao.addUser(new User("test3","","test3@gmail.com","","","","3"));
        userDao.addUser(new User("test4","","test4@gmail.com","","","","4"));
        userDao.addUser(new User("test5","","test5@gmail.com","","","","5"));
        userDao.addUser(new User("test6","","test6@gmail.com","","","","6"));
        userDao.addUser(new User("test7","","test7@gmail.com","","","","7"));
        userDao.addUser(new User("test8","","test8@gmail.com","","","","8"));
        userDao.addUser(new User("test9","","test9@gmail.com","","","","9"));

        System.out.println("\tAttempting to get user from person with id 10:");
        User user= userDao.getUser(new Person("10","","","","","","",""));

        System.out.println(user != null ? "\tFound user with name: " + user.getFirstName() + " " + user.getLastName() : "\tCould not find user");
        System.out.println(user != null ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertNull(user);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

}
