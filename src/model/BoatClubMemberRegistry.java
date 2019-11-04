package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * This class will represent the final object which is
 * serialized and saved to the database.
 * <p>
 * It carries all currently Saved States.
 */
public class BoatClubMemberRegistry {

    private ArrayList<BoatClubMember> boatClubMembers;


    public BoatClubMemberRegistry(){
        this.boatClubMembers = new ArrayList<>();
    }

    /**
     * Adds a state to the current instance.
     *
     * @param objectState
     */
    public void addState(BoatClubMember objectState){
        if(!boatClubMembers.contains(objectState)){
            boatClubMembers.add(objectState);
        }
        else{
            throw new IllegalArgumentException("Can not add duplicate instances."); // this never happen because of unique id's.
        }
    }

    public boolean contains(BoatClubMember objectState){
        return boatClubMembers.contains(objectState);
    }

    /**
     * Overwrites Object in InstanceState.
     * @param objectState State to overwrite with.
     */
    public void updateState(BoatClubMember objectState){
        if(boatClubMembers.contains(objectState)){
            int index = boatClubMembers.indexOf(objectState);
            boatClubMembers.set(index, objectState);
        }

    }

    /**
     * Removes provided state from BoatClubMemberRegistry.
     * @param objectState
     */
    public void removeState(BoatClubMember objectState){
        if(boatClubMembers.contains(objectState)){
            boatClubMembers.remove(objectState);
        }
        else {
            throw new NoSuchElementException("Entry not found.");
        }
    }


    /**
     * Returns the list of currently saved states.
     * @return
     */
    public ArrayList<BoatClubMember> getBoatClubMembers() {
        return boatClubMembers;
    }

    /**
     * Fetch state with id.
     * @param id Unique ID of member.
     * @return Current ID state.
     */
    public BoatClubMember getSavedStateById(String id){
        for(BoatClubMember state: boatClubMembers){
            if(state.getMember().getId().equalsIgnoreCase(id)){
                return state;
            }
        }
        throw new NoSuchElementException("Entry not found.");
    }
}
