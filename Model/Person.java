package Model;

/**
 * A class to hold the data for a Person.
 */
public class Person {

    /**
     * Unique identifier for this person (non-empty string).
     */
    private final String personID;

    /**
     * User (Username) to which this person belongs.
     */
    private final String descendant;

    /**
     * Person's first name (non-empty string).
     */
    private final String firstName;

    /**
     * Person's last name (non-empty string).
     */
    private final String lastName;

    /**
     * Person's gender (string: "f" or "m").
     */
    private final String gender;

    /**
     * ID of person's father (possibly null).
     */
    private final String father;

    /**
     * ID of person's mother (possibly null).
     */
    private final String mother;

    /**
     * ID of person's spouse (possibly null).
     */
    private final String spouse;

    /**
     * Constructor for the Person class.
     * @param personID Unique identifier for this person (non-empty string).
     * @param descendant User (Username) to which this person belongs.
     * @param firstName Person's first name (non-empty string).
     * @param lastName Person's last name (non-empty string).
     * @param gender Person's gender (string: "f" or "m").
     * @param father ID of person's father (possibly null).
     * @param mother ID of person's mother (possibly null).
     * @param spouse ID of person's spouse (possibly null).
     */
    public Person(String personID, String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    public String getPersonID() {
        return personID;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getFather() {
        return father;
    }

    public String getMother() { return mother; }

    public String getSpouse() {
        return spouse;
    }
}
