package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Class which handles communication with database.
 * Handles serialization of BoatClubMemberRegistry.
 */
public class BaseController {

    private final String DB_PATH = "database.json";
    private File dbFile;
    private BoatClubMemberRegistry boatClubMemberRegistry;

    public BaseController() {
        dbFile = new File(DB_PATH);
        verifyDatabaseExist(dbFile);
        fetchDatabase(new Gson());
    }

    /**
     * @param file  Custom database file.
     * @param state Supply a custom BoatClubMemberRegistry.
     */
    public BaseController(File file, BoatClubMemberRegistry state) {

        try {
            this.boatClubMemberRegistry = state;
            this.dbFile = file;
            verifyDatabaseExist(dbFile);
            fetchDatabase(new Gson());

        } catch (Exception e) {
            // Do nothing.
        }

    }

    /**
     * Reads the database into the BoatClubMemberRegistry.
     *
     * @param gson Uses Gson for serializing
     */
    private void fetchDatabase(Gson gson) {
        try {
            String formattedString = Files.readString(dbFile.toPath());
            if (!formattedString.isEmpty()) {
                gson = getTypeAdapter(RuntimeTypeAdapterFactory.of(Boat.class, "type"));
                boatClubMemberRegistry = gson.fromJson(formattedString, BoatClubMemberRegistry.class);
            } else {
                boatClubMemberRegistry = new BoatClubMemberRegistry();
            }
        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }


    /**
     * Verifies that the database exists in file.
     *
     * @param file File to examine.
     */
    private void verifyDatabaseExist(File file){
        try {
            if (!databaseExists()) {
                file = new File(DB_PATH);
                file.createNewFile();
            }
        }
        catch (IOException e ){
            e.printStackTrace();
        }

    }


    /**
     * Verifies the existence of a database entry.
     *
     * @return boolean
     */
    private boolean databaseExists() {
        return dbFile != null && dbFile.exists();
    }

    /**
     * Function responsible for updating the database file.
     */
    private void writeToDB(Gson gsonObj){
        try {
            gsonObj = getTypeAdapter(RuntimeTypeAdapterFactory.of(Boat.class, "type"));
            String formatted = gsonObj.toJson(boatClubMemberRegistry);
            Files.writeString(this.dbFile.toPath(), formatted);
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds or overwrites state depending on previous entries.
     *
     * @param currentMember
     */
    protected void addToRegistry(BoatClubMember currentMember) throws IOException {
        if (boatClubMemberRegistry.contains(currentMember)) {
            boatClubMemberRegistry.update(currentMember);
        } else {
            while (boatClubMemberRegistry.hasMemberById(currentMember.getMember().getId())) {
                currentMember.getMember().setId();
            }

            boatClubMemberRegistry.add(currentMember);
        }
        writeToDB(new Gson());
    }

    /**
     * Removes state object.
     *
     * @param state
     */
    protected boolean removeFromRegistry(BoatClubMember state) {
        if (this.boatClubMemberRegistry.getBoatClubMembers().contains(state)) {
            this.boatClubMemberRegistry.remove(state);
            writeToDB(new Gson());
            return true;
        }
        return false;
    }


    /**
     * Returns a BoatClubMember entry.
     *
     * @param id Unique member id.
     * @return A BoatClubMember object.
     */
    protected BoatClubMember getMemberById(String id) {
        try {
            return this.boatClubMemberRegistry.getMemberById(id);
        } catch (Exception e) {
            return null;
        }
    }

    protected ArrayList<BoatClubMember> getRegistry() {
        return boatClubMemberRegistry.getBoatClubMembers();
    }


    /**
     * Credits: https://www.novatec-gmbh.de/en/blog/gson-object-hierarchies/
     * <p>
     * Provides a JSON formatter with a type adapter
     * so that each Subtype of Boat can be correctly serialized and de-serialized.
     *
     * @return
     */
    private Gson getTypeAdapter(RuntimeTypeAdapterFactory<Boat> vehicleAdapterFactory) {
        RuntimeTypeAdapterFactory<Boat> factory = vehicleAdapterFactory
                .registerSubtype(Canoe.class, "Canoe")
                .registerSubtype(Kayak.class, "Kayak")
                .registerSubtype(MotorBoat.class, "MotorBoat")
                .registerSubtype(Other.class, "Other")
                .registerSubtype(SailBoat.class, "SailBoat");

        return new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(factory).create();
    }


}