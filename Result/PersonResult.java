package Result;

import Model.Person;

import java.util.ArrayList;

/**
 * A class for person results.
 */
public class PersonResult extends MsgResult {

    /**
     * Array of Person objects (may be null).
     */
    private final ArrayList<Person> data;

    /**
     * Name of user account this person belongs to.
     */
    private final String descendant;

    /**
     * Person's unique ID.
     */
    private final String personID;

    /**
     * Person's first name.
     */
    private final String firstName;

    /**
     * Person's last name.
     */
    private final String lastName;

    /**
     * Person's gender ("m" or "f").
     */
    private final String gender;

    /**
     * ID of person's father (may be null).
     */
    private final String father;

    /**
     * ID of person's mother (may be null).
     */
    private final String mother;

    /**
     * ID of person's spouse (may be null).
     */
    private final String spouse;

    /**
     * Constructor for PersonResult class representing a single person.
     * @param descendant Name of user account this person belongs to.
     * @param personID Person's unique ID.
     * @param firstName Person's first name.
     * @param lastName Person's last name.
     * @param gender Person's gender ("m" or "f").
     * @param father ID of person's father (may be null).
     * @param mother ID of person's mother (may be null).
     * @param spouse ID of person's spouse (may be null).
     */
    public PersonResult(String descendant, String personID, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        super();
        this.data = null;
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    /**
     * Constructor for PersonResult class representing multiple people.
     * @param data Array of Person objects (may be null).
     */
    public PersonResult(ArrayList<Person> data) {
        super();
        this.data = data;
        this.descendant = null;
        this.personID = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.father = null;
        this.mother = null;
        this.spouse = null;
    }

    public ArrayList<Person> getData() {
        return data;
    }
}
