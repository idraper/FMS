package DAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class for functions that must be employed by each DAO class.
 */
public abstract class IDao {

    /**
     * Constructor for the IDao class, which simply creates the SQL handler.
     */
    public IDao() {}

    /**
     * Every DAO must be able to create a table for that class.
     */
    public abstract void createTable();

    /**
     * Every DAO must be able to clear the table for that class.
     */
    public abstract boolean clearTable();

    /**
     * Every DAO must be able to convert their SQL request to the corresponding Model object.
     * @param data An ArrayList of HashMaps, where each HashMap is the data for a row return from the database.
     * @return An object corresponding to their Model, null if no entries were found in the database.
     */
    public abstract Object toObject(ArrayList<HashMap<String,Object>> data);

}
