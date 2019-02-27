package Tests;

import DAO.PersonDao;
import DAO.SQLHandler;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * A class to run a series of tests on the PersonDao class.
 */
public class PersonDaoTests {

    @Test
    public void clearPositiveTest() {
        System.out.println("Running Test: Clear Person's Table From Database POSITIVE");
        PersonDao personDao = new PersonDao();
        personDao.clearTable();
        personDao.addPerson(new Person("0","","","","","","",""));
        personDao.addPerson(new Person("1","","","","","","",""));
        personDao.addPerson(new Person("2","","","","","","",""));
        personDao.addPerson(new Person("3","","","","","","",""));
        personDao.addPerson(new Person("4","","","","","","",""));
        personDao.addPerson(new Person("5","","","","","","",""));
        personDao.addPerson(new Person("6","","","","","","",""));
        personDao.addPerson(new Person("7","","","","","","",""));
        personDao.addPerson(new Person("8","","","","","","",""));
        personDao.addPerson(new Person("9","","","","","","",""));

        SQLHandler.connect();
        int numRowsBefore = SQLHandler.getNumRows("Persons");

        personDao.clearTable();

        int numRowsAfter = SQLHandler.getNumRows("Persons");
        SQLHandler.close();

        System.out.println(String.format("\tNumber of rows before clear: %d\n\tNumber of rows after clear: %d", numRowsBefore, numRowsAfter));
        System.out.println(numRowsAfter == 0 ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertTrue(numRowsAfter == 0);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void clearNegativeTest() {
        System.out.println("Running Test: Clear Person's Table From Database NEGATIVE");
        PersonDao personDao = new PersonDao();
        personDao.clearTable();

        SQLHandler.connect();

        try {
            SQLHandler.execute_no_rtn("DROP TABLE Persons");
        } catch (SQLException e) {}

        SQLHandler.close();

        System.out.println(String.format("\tAttempting to clear table when it does not exist (should fail):"));
        boolean cleared = personDao.clearTable();

        Assertions.assertFalse(cleared);

        System.out.println(cleared ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void addPositiveTest() {
        System.out.println("Running Test: Add person to Database POSITIVE");
        PersonDao personDao = new PersonDao();
        personDao.clearTable();

        System.out.println("\tAttempting to add person with id 0:");
        boolean added = personDao.addPerson(new Person("0","","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println("\tAttempting to add person with id 1:");
        added = personDao.addPerson(new Person("1","","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println("\tAttempting to add person with id 2:");
        added = personDao.addPerson(new Person("2","","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void addNegativeTest() {
        System.out.println("Running Test: Add person to Database NEGATIVE");
        PersonDao personDao = new PersonDao();
        personDao.clearTable();

        System.out.println("\tAttempting to add person with id 0:");
        boolean added = personDao.addPerson(new Person("0","","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertTrue(added);

        System.out.println("\tAttempting to add person with id 0:");
        added = personDao.addPerson(new Person("0","","","","","","",""));
        System.out.println(added ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");
        Assertions.assertFalse(added);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void getPositiveTest() {
        System.out.println("Running Test: Get person from Database POSITIVE");
        PersonDao personDao = new PersonDao();
        personDao.clearTable();

        personDao.addPerson(new Person("0","","Isaac","Draper","m","","",""));
        personDao.addPerson(new Person("1","","","","","","",""));
        personDao.addPerson(new Person("2","","","","","","",""));
        personDao.addPerson(new Person("3","","","","","","",""));
        personDao.addPerson(new Person("4","","","","","","",""));
        personDao.addPerson(new Person("5","","","","","","",""));
        personDao.addPerson(new Person("6","","","","","","",""));
        personDao.addPerson(new Person("7","","","","","","",""));
        personDao.addPerson(new Person("8","","","","","","",""));
        personDao.addPerson(new Person("9","","","","","","",""));

        System.out.println("\tAttempting to get user with personId 0:");
        Person person = personDao.getPerson(new User("idraper","whoa","test@gmail.com","Isaac","Draper","m","0"));

        System.out.println(person != null ? "\tFound person with name: " + person.getFirstName() + " " + person.getLastName() : "\tCould not find person");
        System.out.println(person != null ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getPersonID(), "0");
        Assertions.assertEquals(person.getFirstName(), "Isaac");
        Assertions.assertEquals(person.getLastName(), "Draper");

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Test
    public void getNegativeTest() {
        System.out.println("Running Test: Get person from Database NEGATIVE");
        PersonDao personDao = new PersonDao();
        personDao.clearTable();

        personDao.addPerson(new Person("0","","Isaac","Draper","m","","",""));
        personDao.addPerson(new Person("1","","","","","","",""));
        personDao.addPerson(new Person("2","","","","","","",""));
        personDao.addPerson(new Person("3","","","","","","",""));
        personDao.addPerson(new Person("4","","","","","","",""));
        personDao.addPerson(new Person("5","","","","","","",""));
        personDao.addPerson(new Person("6","","","","","","",""));
        personDao.addPerson(new Person("7","","","","","","",""));
        personDao.addPerson(new Person("8","","","","","","",""));
        personDao.addPerson(new Person("9","","","","","","",""));

        System.out.println("\tAttempting to get user with personId 10:");
        Person person = personDao.getPerson(new User("bleh","meh","hmmm@gmail.com","Blob","Macpat","m","10"));

        System.out.println(person != null ? "\tFound person with name: " + person.getFirstName() + " " + person.getLastName() : "\tCould not find person");
        System.out.println(person != null ? "\t\tCOMPLETED TASK" : "\t\tDID NOT COMPLETE TASK");

        Assertions.assertNull(person);

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }

}
