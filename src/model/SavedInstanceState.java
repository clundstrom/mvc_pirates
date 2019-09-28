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
            throw new IllegalArgumentException("Can not add duplicate objects."); // this never happen because of unique id's.
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


    public ArrayList<SavedState> getSavedStates() {
        return savedStates;
    }

    public SavedState getSavedStateById(String id){
        for(SavedState state: savedStates){
            if(state.getMember().getId() == id){
                return state;
            }
        }
        throw new NoSuchElementException("Entry not found.");
    }
}
