package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class will represent the final object which is
 * serialized and saved to our database.
 * <p>
 * It carries a list of generic Key/Value pairs.
 */
public class SavedInstanceState {

    private List<Map<Object, Object>> instanceObjects;
    private static SavedInstanceState savedInstanceState;


    /**
     * Using singleton pattern to avoid duplicate InstanceStates.
     *
     * @return A singleton object.
     */
    public static SavedInstanceState getInstance() {
        if(savedInstanceState == null){
            savedInstanceState = new SavedInstanceState();
        }
        return savedInstanceState;
    }

    SavedInstanceState(){
        this.instanceObjects = new ArrayList<>();
    }

}
