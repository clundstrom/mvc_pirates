package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.SavedInstanceState;
import model.SavedState;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class which handles communication with database.
 * Handles serialization of SavedInstanceState.
 */
public class BaseController {

    private final String DB_PATH = "src\\assets\\database.json";
    private File dbFile;
    private SavedInstanceState savedInstanceState;

    public BaseController() {
        dbFile = new File(DB_PATH);
        verifyDatabaseExist();
        fetchDatabase();
    }


    /**
     * Reads the database into the SavedInstanceState.
     */
    private void fetchDatabase() {
        try {
            String formattedString = Files.readString(dbFile.toPath());
            if (!formattedString.isEmpty()) {
                savedInstanceState = new Gson().fromJson(formattedString, SavedInstanceState.class);
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
     * Creates a database json file if none exist.
     */
    private void verifyDatabaseExist() {
        try {
            if (!databaseExists()) {
                dbFile = new File(DB_PATH);
                dbFile.createNewFile();
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
    private void writeToDB() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String formatted = gson.toJson(savedInstanceState);
        try {
            Files.writeString(this.dbFile.toPath(), formatted, StandardCharsets.UTF_8);
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
        writeToDB();
    }

    /**
     * Removes state object.
     * @param state
     */
    protected boolean removeFromInstanceState(SavedState state){
        if(this.savedInstanceState.getSavedStates().contains(state)){
            this.savedInstanceState.removeState(state);
            writeToDB();
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
}