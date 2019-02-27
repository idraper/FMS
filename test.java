import DAO.PersonDao;
import DAO.UserDao;
import Model.Person;
import Model.User;

public class test {

    public static void main(String[] args) {
        User user = new User("isaac","meh","test@gmail.com","Isaac","Draper","m","55");
        Person person = new Person("55", "", "Isaac", "Draper", "m", null, null, null);

        UserDao userDao = new UserDao();
        PersonDao personDao = new PersonDao();
        // userDao.clearTable();
        // personDao.clearTable();

        userDao.addUser(user);
        User test = userDao.getUser(new Person("55", "", "","","",null,null,null));
        System.out.println(test.getEmail());
        System.out.println();

        personDao.addPerson(person);
        Person testP = personDao.getPerson(user);

        System.out.println(testP.getFirstName());

    }
}
