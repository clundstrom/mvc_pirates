package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.*;
import view.BaseView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class which handles communication with database.
 * Handles serialization of BoatClubMemberRegistry.
 */
public class BaseController {

    private final String DB_PATH = "database.json";
    private File dbFile;
    private BoatClubMemberRegistry boatClubMemberRegistry;
    private BaseView view;

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
        this.boatClubMemberRegistry = state;
        this.dbFile = file;
        verifyDatabaseExist(dbFile);
        fetchDatabase(new Gson());
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

<<<<<<< HEAD
        }
        catch (JsonSyntaxException syntax){
            view.errorMessage();
        }
        catch (IOException e) {
            view.errorMessage();
        }
        catch (Exception e ){
            view.errorMessage();
=======
        } catch (JsonSyntaxException syntax) {
            System.out.println("Database file is corrupt. Check syntax or delete file.");
        } catch (IOException e) {
            System.out.println("There was an error reading the database.");
        } catch (Exception e) {
            System.out.println("There was an error.");
>>>>>>> bdc5fdfdb3395c67e77d317f6b7c150665cb4c64
        }
    }


    /**
     * Verifies that the database exists in file.
     *
     * @param file File to examine.
     */
    private void verifyDatabaseExist(File file) {
        try {
            if (!databaseExists()) {
                file = new File(DB_PATH); 
                file.createNewFile();
            }
        } catch (IOException e) {
            view.errorMessage();
        } catch (Exception e) {
            view.errorMessage();
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
        String formatted = gsonObj.toJson(boatClubMemberRegistry);
        try {
            Files.writeString(this.dbFile.toPath(), formatted);
<<<<<<< HEAD
        }
        catch (IOException e ){
            view.errorMessage();
=======
        } catch (IOException e) {
            System.out.println("There was an error writing to the database.");
>>>>>>> bdc5fdfdb3395c67e77d317f6b7c150665cb4c64
        }
    }

    /**
     * Adds or overwrites state depending on previous entries.
     *
     * @param state
     */
    protected void addToRegistry(BoatClubMember state) {
        if (boatClubMemberRegistry.contains(state)) {
            boatClubMemberRegistry.update(state);
        } else {
            while(boatClubMemberRegistry.hasMemberById(state.getMember().getId())){
                state.getMember().setId();
            }

            boatClubMemberRegistry.add(state);
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
<<<<<<< HEAD
    protected BoatClubMember getMemberById(String id){
       try{
           return this.boatClubMemberRegistry.getMemberById(id);
       }
       catch (NoSuchElementException e){
           view.idNotFound(id);
       }
       return null;
=======
    protected BoatClubMember getMemberById(String id) {
        try {
            return this.boatClubMemberRegistry.getMemberById(id);
        } catch (NoSuchElementException e) {
            System.out.println("Could not find an entry with id: " + id);
        }
        return null;
>>>>>>> bdc5fdfdb3395c67e77d317f6b7c150665cb4c64
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
                .registerSubtype(Boat.class, "Canoe")
                .registerSubtype(Kayak.class, "Kayak")
                .registerSubtype(MotorBoat.class, "MotorBoat")
                .registerSubtype(Other.class, "Other")
                .registerSubtype(SailBoat.class, "SailBoat");

        return new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(factory).create();
    }


}