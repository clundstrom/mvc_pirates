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


    public BoatClubMemberRegistry() {
        this.boatClubMembers = new ArrayList<>();
    }

    /**
     * Adds a BoatClubMember to the current instance.
     *
     * @param objectState
     */
    public void add(BoatClubMember objectState) {
        boatClubMembers.add(objectState);
    }

    public boolean contains(BoatClubMember objectState) {
        return boatClubMembers.contains(objectState);
    }

    /**
     * Overwrites Object in InstanceState.
     *
     * @param boatClubMember State to overwrite with.
     */
    public void update(BoatClubMember boatClubMember) {
        int index = boatClubMembers.indexOf(boatClubMember);
        boatClubMembers.set(index, boatClubMember);
    }

    /**
     * Removes provided state from BoatClubMemberRegistry.
     *
     * @param objectState
     */
    public void remove(BoatClubMember objectState) {
        boatClubMembers.remove(objectState);
    }


    /**
     * Returns the list of currently saved states.
     *
     * @return
     */
    public ArrayList<BoatClubMember> getBoatClubMembers() {
        return boatClubMembers;
    }

    /**
     * Fetch BoatClubMember by id.
     *
     * @param id Unique ID of member.
     * @return Current ID state.
     */
    public BoatClubMember getMemberById(String id) {
        for (BoatClubMember boatClubMember : boatClubMembers) {
            if (boatClubMember.getMember().getId().equalsIgnoreCase(id)) {
                return boatClubMember;
            }
        }
        throw new NoSuchElementException("Entry not found.");
    }

    public boolean hasMemberById(String id) {
        for (BoatClubMember member : boatClubMembers) {
            if (member.getMember().getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
