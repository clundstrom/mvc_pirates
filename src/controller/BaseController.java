package controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.SavedInstanceState;
import model.SavedState;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
        verifyDatabase();
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
    private void verifyDatabase() {
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
        if (dbFile != null && dbFile.exists())
            return true;
        else
            return false;
    }

    /**
     * Function responsible for updating the database file.
     */
    private void writeToDB() {
        String formatted = new Gson().toJson(savedInstanceState);
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
    protected void removeFromInstanceState(SavedState state){
        if(this.savedInstanceState.getSavedStates().contains(state)){
            this.savedInstanceState.removeState(state);
        }
    }


    /**
     * Returns a registry SavedState entry.
     *
     * @param id Unique member id.
     * @return A SavedState object.
     */
    protected SavedState getStateById(String id){
       return this.savedInstanceState.getSavedStateById(id);
    }


}