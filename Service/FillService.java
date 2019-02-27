package Service;

import DAO.EventDao;
import DAO.PersonDao;
import DAO.SQLHandler;
import DAO.UserDao;
import FillData.Location;
import Model.Event;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Result.FillResult;
import Result.MsgResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Service that populates the server's database with generated data for the specified user name.
 */
public class FillService extends MsgResult {

    private ArrayList<String> mNames;
    private ArrayList<String> fNames;
    private ArrayList<String> sNames;
    private ArrayList<Location> locations;

    /**
     * Constructor for FillService class.
     */
    public FillService() {
    }

    /**
     * Populates the server's database with generated data for the specified user name. The required "username" parameter must be a user already registered with the server. If there is any data in the database already associated with the given user name, it is deleted.
     * @param fillRequest A FillRequest object from a request.
     * @return A FillResult object after completing tasks.
     */
    public MsgResult fill(FillRequest fillRequest) throws IOException {
        SQLHandler.connect();
        try {
            PersonDao personDao = new PersonDao();
            EventDao eventDao = new EventDao();
            UserDao userDao = new UserDao();

            User user = userDao.getUser(fillRequest.getUserName());
            if (user == null) return new MsgResult("User does not exist");

            personDao.removeUser(user);
            eventDao.removeUser(user);

            Gson gson = new Gson();
            mNames = gson.fromJson(Files.readString(Paths.get("src/FillData/mnames.json")).replaceAll("\\n", ""), new TypeToken<ArrayList<String>>() {
            }.getType());
            fNames = gson.fromJson(Files.readString(Paths.get("src/FillData/fnames.json")).replaceAll("\\n", ""), new TypeToken<ArrayList<String>>() {
            }.getType());
            sNames = gson.fromJson(Files.readString(Paths.get("src/FillData/snames.json")).replaceAll("\\n", ""), new TypeToken<ArrayList<String>>() {
            }.getType());
            locations = gson.fromJson(Files.readString(Paths.get("src/FillData/locations.json")).replaceAll("\\n", ""), new TypeToken<ArrayList<Location>>() {
            }.getType());

            String personID = UUID.randomUUID().toString();
            String[] parentIDs = addCouple(user, personDao, eventDao, fillRequest.getGenerations(), 1945, 1945);

            personDao.addPerson(new Person(personID,
                    user.getUserName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getGender(),
                    parentIDs[1],
                    parentIDs[0],
                    null));

            int numAdded = (int) Math.pow(2, fillRequest.getGenerations() + 1) - 1;
            return new FillResult("Successfully added " + Integer.toString(numAdded) + " persons and " + Integer.toString(numAdded * 3 - 3) + " events to the database.");
        }
        finally {
            final long startTime = System.currentTimeMillis();
            SQLHandler.execute_batch();
            SQLHandler.close();
            final long endTime = System.currentTimeMillis();
            System.out.println("Execution Time: " + (endTime - startTime));
            System.out.println();
        }
    }

    private String[] addCouple(User user, PersonDao personDao, EventDao eventDao, int gen, int mBirth, int fBirth) {
        if (gen == 0) {
            String[] rtn = new String[2]; rtn[0] = null; rtn[1] = null;
            return rtn;
        }

        // Data that is the same for both
        String fID = UUID.randomUUID().toString();
        String mID = UUID.randomUUID().toString();
        Location marriageLoc = getRandomLocation();

        int age = 70;
        int yearsBeforeMarried = 24;
        int yearsBeforeChildren = 26;

        // -----------------------------------------------------------------------------------------------------
        // Everything with the father
        String[] parentIDs = addCouple(user, personDao, eventDao, gen-1, mBirth-yearsBeforeChildren, fBirth-yearsBeforeChildren);
        String motherID = parentIDs[0];
        String fatherID = parentIDs[1];

        addPerson(personDao, mID, user, "m", fatherID, motherID, fID);

        Location birthLoc = getRandomLocation();
        Location deathLoc = getRandomLocation();

        String eventID = UUID.randomUUID().toString();
        eventDao.addEvent(eventID, user.getUserName(), mID, marriageLoc.getLatitutde(), marriageLoc.getLongitude(), marriageLoc.getCountry(), marriageLoc.getCity(), "marriage", mBirth+yearsBeforeMarried);
        eventID = UUID.randomUUID().toString();
        eventDao.addEvent(eventID, user.getUserName(), mID, birthLoc.getLatitutde(), birthLoc.getLongitude(), birthLoc.getCountry(), birthLoc.getCity(), "birth", mBirth);
        eventID = UUID.randomUUID().toString();
        eventDao.addEvent(eventID, user.getUserName(), mID, deathLoc.getLatitutde(), deathLoc.getLongitude(), deathLoc.getCountry(), deathLoc.getCity(), "death", mBirth+age);

        // -----------------------------------------------------------------------------------------------------
        // Everything with the mother
        parentIDs = addCouple(user, personDao, eventDao, gen-1, mBirth-age, fBirth-age);
        motherID = parentIDs[0];
        fatherID = parentIDs[1];

        addPerson(personDao, fID, user, "f", fatherID, motherID, mID);

        birthLoc = getRandomLocation();
        deathLoc = getRandomLocation();

        eventID = UUID.randomUUID().toString();
        eventDao.addEvent(eventID, user.getUserName(), fID, marriageLoc.getLatitutde(), marriageLoc.getLongitude(), marriageLoc.getCountry(), marriageLoc.getCity(), "marriage", fBirth+yearsBeforeMarried);
        eventID = UUID.randomUUID().toString();
        eventDao.addEvent(eventID, user.getUserName(), fID, birthLoc.getLatitutde(), birthLoc.getLongitude(), birthLoc.getCountry(), birthLoc.getCity(), "birth", fBirth);
        eventID = UUID.randomUUID().toString();
        eventDao.addEvent(eventID, user.getUserName(), fID, deathLoc.getLatitutde(), deathLoc.getLongitude(), deathLoc.getCountry(), deathLoc.getCity(), "death", fBirth+age);

        //  Recursive return
        String[] rtn = new String[2];
        rtn[0] = fID;
        rtn[1] = mID;
        return rtn;
    }

    private void addPerson(PersonDao personDao, String personID, User user, String gender, String fatherID, String motherID, String spouseID) {
        personDao.addPerson(new Person(
                personID,
                user.getUserName(),
                getRandomName(gender == "m" ? mNames : fNames),
                getRandomName(sNames),
                gender,
                fatherID,
                motherID,
                spouseID));
    }

    private String getRandomName(ArrayList<String> data) {
        return data.get((new Random()).nextInt(data.size()));
    }

    private Location getRandomLocation () {
        return locations.get((new Random()).nextInt(locations.size()));
    }

}
