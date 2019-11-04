package controller;

import model.Boat;
import model.BoatClubMember;
import model.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Controller which handles CRUD operations on the
 * registry of Members and Boats.
 */
public class RegisterController extends BaseController {


    private BoatClubMember currentMember;

    public RegisterController() {
        currentMember = new BoatClubMember();
    }

    /**
     * Receives updates and registers member info with controller.
     *
     * @param updatedMember Member which is updated.
     */
    public boolean onMemberUpdated(Member updatedMember) {
        // Check if member exists
        if (currentMember.hasMember()) {
            if (!updatedMember.getName().isEmpty()) {
                this.currentMember.getMember().setName(updatedMember.getName());
            }

            if (!updatedMember.getPersonalNumber().isEmpty()) {
                this.currentMember.getMember().setPersonalNumber(updatedMember.getPersonalNumber());
            }
            saveBoatClubMember(this.currentMember);
            return true;
        }
        return false;
    }

    public boolean onMemberCreated(Member member) {
        currentMember = new BoatClubMember();
        currentMember.setMember(member);
        saveBoatClubMember(currentMember);
        return true;
    }


    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param id Id of a member.
     */
    public boolean onMemberDeleted(String id) {
        try {
            if (removeFromRegistry(getMemberById(id))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Receives updates and registers boat info with controller.
     *
     * @param boat Boat.
     */
    public boolean onBoatCreated(Boat boat) {
        if (currentMember.getBoats().contains(boat)) {
            return false;
        } else {
            currentMember.getBoats().add(boat);
        }
        saveBoatClubMember(currentMember);
        return true;
    }

    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param index Index of boat.
     */
    public boolean onBoatDeleted(int index) {
        if (index < currentMember.getBoats().size()) {
            currentMember.getBoats().remove(index);
            saveBoatClubMember(currentMember);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Updates corresponding boat.
     *
     * @param index Index of boat to update.
     */
    public boolean onBoatUpdated(int index) {
        if (index < currentMember.getBoats().size()) {
            currentMember.getBoats();
            return saveBoatClubMember(currentMember);
        } else {
            return false;
        }

    }

    /**
     * Registers the current state with the base controller.
     */
    public boolean saveBoatClubMember(BoatClubMember state) {
        try {
            addToRegistry(state);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Queries DB for Saved State. Sets local state if found and returns true.
     *
     * @param id Member id.
     * @return True/false
     */
    public boolean hasMemberId(String id) {
        this.currentMember = getMemberById(id);

        return currentMember != null;
    }

    /**
     * @return saved Returns saved state's current member.
     */
    public Member getMember() {
        if (currentMember.hasMember()) {
            return currentMember.getMember();
        }
        throw new NullPointerException("No member found");
    }

    /**
     * @return Returns boats of current state.
     */
    public ArrayList<Boat> getBoats() {
        if (currentMember.hasBoats()) {
            return currentMember.getBoats();
        }
        throw new NoSuchElementException("There was an error while retrieving your boats.");
    }

    public ArrayList<BoatClubMember> getRegistry() {
        return super.getRegistry();
    }
}