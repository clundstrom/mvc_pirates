package controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.Member;
import model.SavedInstanceState;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
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
        this.savedInstanceState = SavedInstanceState.getInstance();
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
    void setDatabaseEntry() {
        String formatted = new Gson().toJson(savedInstanceState);
        try {
            Files.writeString(this.dbFile.toPath(), formatted, StandardCharsets.UTF_8);
        }
        catch (IOException e ){
            System.out.println("There was an error writing to the database.");
        }
    }


}