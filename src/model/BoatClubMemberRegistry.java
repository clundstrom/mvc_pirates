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
     * @param currentMember
     */
    public void add(BoatClubMember currentMember) {
        while (hasMemberById(currentMember.getMember().getId())) {
            currentMember.getMember().setId();
        }
        boatClubMembers.add(currentMember);
    }

    /**
     * Checks if a certain member exists in the registry.
     * @param currentMember A BoatClubMember.
     * @return true/false
     */
    public boolean contains(BoatClubMember currentMember) {
        return boatClubMembers.contains(currentMember);
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
     * @param currentMember
     */
    public void remove(BoatClubMember currentMember) {
        boatClubMembers.remove(currentMember);
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

    /**
     * Checks if a certain ID exists in the member registry.
     * @param id String id.
     * @return true/false
     */
    private boolean hasMemberById(String id) {
        for (BoatClubMember member : boatClubMembers) {
            if (member.getMember().getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
