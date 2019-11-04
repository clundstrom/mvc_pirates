package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * This class will represent the final object which is
 * serialized and saved to the database.
 * <p>
 * It carries all currently saved BoatClubMembers.
 */
public class BoatClubMemberRegistry {

    private ArrayList<BoatClubMember> boatClubMembers;


    public BoatClubMemberRegistry(){
        this.boatClubMembers = new ArrayList<>();
    }

    /**
     * Adds a BoatClubMember to the current instance.
     *
     * @param objectState
     */
    public void add(BoatClubMember objectState){
        if(!boatClubMembers.contains(objectState)){
            boatClubMembers.add(objectState);
        }
        else{
            throw new IllegalArgumentException("Can not add duplicate instances.");
        }
    }

    public boolean contains(BoatClubMember objectState){
        return boatClubMembers.contains(objectState);
    }

    /**
     * Overwrites Object in InstanceState.
     * @param objectState State to overwrite with.
     */
    public void update(BoatClubMember objectState){
        if(boatClubMembers.contains(objectState)){
            int index = boatClubMembers.indexOf(objectState);
            boatClubMembers.set(index, objectState);
        }

    }

    /**
     * Removes provided state from BoatClubMemberRegistry.
     * @param objectState
     */
    public void remove(BoatClubMember objectState){
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
     * Fetch BoatClubMember by id.
     * @param id Unique ID of member.
     * @return Current ID state.
     */
    public BoatClubMember getMemberById(String id){
        for(BoatClubMember state: boatClubMembers){
            if(state.getMember().getId().equalsIgnoreCase(id)){
                return state;
            }
        }
        throw new NoSuchElementException("Entry not found.");
    }
}
