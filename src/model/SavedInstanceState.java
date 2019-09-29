package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * This class will represent the final object which is
 * serialized and saved to the database.
 * <p>
 * It carries all currently Saved States.
 */
public class SavedInstanceState {

    private ArrayList<SavedState> savedStates;


    public SavedInstanceState(){
        this.savedStates = new ArrayList<>();
    }

    public void addState(SavedState objectState){
        if(!savedStates.contains(objectState)){
            savedStates.add(objectState);
        }
        else{
            throw new IllegalArgumentException("Can not add duplicate instances."); // this never happen because of unique id's.
        }
    }

    public boolean contains(SavedState objectState){
        return savedStates.contains(objectState);
    }

    /**
     * Overwrites Object in InstanceState.
     * @param objectState State to overwrite with.
     */
    public void updateState(SavedState objectState){
        if(savedStates.contains(objectState)){
            int index = savedStates.indexOf(objectState);
            savedStates.set(index, objectState);
        }

    }

    public void removeState(SavedState objectState){
        if(savedStates.contains(objectState)){
            savedStates.remove(objectState);
        }
        else {
            throw new NoSuchElementException("Entry not found.");
        }
    }


    /**
     * Returns the list of currently saved states.
     * @return
     */
    public ArrayList<SavedState> getSavedStates() {
        return savedStates;
    }

    /**
     * Fetch state with id.
     * @param id Unique ID of member.
     * @return Current ID state.
     */
    public SavedState getSavedStateById(String id){
        for(SavedState state: savedStates){
            if(state.getMember().getId().equalsIgnoreCase(id)){
                return state;
            }
        }
        throw new NoSuchElementException("Entry not found.");
    }
}
