package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class which handles communication with database.
 * Handles serialization of SavedInstanceState.
 */
public class BaseController {

    private final String DB_PATH = "database.json";
    private File dbFile;
    private SavedInstanceState savedInstanceState;

    public BaseController() {
        dbFile = new File(DB_PATH);
        verifyDatabaseExist(dbFile);
        fetchDatabase(new Gson());
    }

    /**
     * @param file Custom database file.
     * @param state Supply a custom SavedInstanceState.
     */
    public BaseController(File file, SavedInstanceState state) {
        this.savedInstanceState = state;
        this.dbFile = file;
        verifyDatabaseExist(dbFile);
        fetchDatabase(new Gson());
    }

    /**
     * Reads the database into the SavedInstanceState.
     * @param gson Uses Gson for serializing
     */
    private void fetchDatabase(Gson gson) {
        try {
            String formattedString = Files.readString(dbFile.toPath());
            if (!formattedString.isEmpty()) {
                gson = getTypeAdapter(RuntimeTypeAdapterFactory.of(Boat.class, "type"));
                savedInstanceState = gson.fromJson(formattedString, SavedInstanceState.class);
            }
            else{
                savedInstanceState = new SavedInstanceState();
            }

        }
        catch (JsonSyntaxException syntax){
            System.out.println("Database file is corrupt. Check syntax or delete file.");
        }
        catch (IOException e) {
            System.out.println("There was an error reading the database.");
        }
        catch (Exception e ){
            System.out.println("There was an error.");
        }
    }


    /**
     * Verifies that the database exists in file.
     * @param file File to examine.
     */
    private void verifyDatabaseExist(File file) {
        try {
            if (!databaseExists()) {
                file = new File(DB_PATH);
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("There was an error generating the database.");
        } catch (Exception e) {
            System.out.println("There was an error.");
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
    private void writeToDB(Gson gsonObj) {
        gsonObj = getTypeAdapter(RuntimeTypeAdapterFactory.of(Boat.class, "type"));
        String formatted = gsonObj.toJson(savedInstanceState);
        try {
            Files.writeString(this.dbFile.toPath(), formatted);
        }
        catch (IOException e ){
            System.out.println("There was an error writing to the database.");
        }
    }

    /**
     * Adds or overwrites state depending on previous entries.
     * @param state
     */
    protected void addToInstanceState(SavedState state){
        if(savedInstanceState.contains(state)){
            savedInstanceState.updateState(state);
        }
        else {
            savedInstanceState.addState(state);
        }
        writeToDB(new Gson());
    }

    /**
     * Removes state object.
     * @param state
     */
    protected boolean removeFromInstanceState(SavedState state){
        if(this.savedInstanceState.getSavedStates().contains(state)){
            this.savedInstanceState.removeState(state);
            writeToDB(new Gson());
            return true;
        }
        return false;
    }


    /**
     * Returns a registry SavedState entry.
     *
     * @param id Unique member id.
     * @return A SavedState object.
     */
    protected SavedState getStateById(String id){
       try{
           return this.savedInstanceState.getSavedStateById(id);
       }
       catch (NoSuchElementException e){
           System.out.println("Could not find an entry with id: " + id);
       }
       return null;
    }

    protected ArrayList<SavedState> getStates(){
        return savedInstanceState.getSavedStates();
    }


    /**
     * Credits: https://www.novatec-gmbh.de/en/blog/gson-object-hierarchies/
     *
     * Provides a JSON formatter with a type adapter
     * so that each Subtype of Boat can be correctly serialized.
     *
     * @return
     */
    private Gson getTypeAdapter(RuntimeTypeAdapterFactory<Boat> vehicleAdapterFactory){
        RuntimeTypeAdapterFactory<Boat> factory = vehicleAdapterFactory
                .registerSubtype(Canoe.class, "Canoe")
                .registerSubtype(Kayak.class, "Kayak")
                .registerSubtype(MotorBoat.class, "MotorBoat")
                .registerSubtype(Other.class, "Other")
                .registerSubtype(SailBoat.class, "SailBoat");

        return new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(vehicleAdapterFactory).create();
    }


}