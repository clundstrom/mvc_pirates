package controller;

import model.Boat;
import model.BoatClubMember;
import model.Member;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Controller which handles CRUD operations on the
 * registry of Members and Boats.
 */
public class RegisterController extends BaseController {


    private BoatClubMember currentState;

    public RegisterController() {
        currentState = new BoatClubMember();
    }

    /**
     * Provides the ability to initialize a controller with a custom saved state.
     *
     * @param boatClubMember
     */
    public RegisterController(BoatClubMember boatClubMember) {
        currentState = boatClubMember;
    }


    /**
     * Receives updates and registers member info with controller.
     *
     * @param updatedMember Member which is updated.
     */
    public boolean onMemberUpdated(Member updatedMember) {
        // Check if member exists
        if (currentState.hasMember()) {
            if (!updatedMember.getName().isEmpty()) {
                this.currentState.getMember().setName(updatedMember.getName());
            }

            if (!updatedMember.getPersonalNumber().isEmpty()) {
                this.currentState.getMember().setPersonalNumber(updatedMember.getPersonalNumber());
            }
            registerSavedState(this.currentState);
            return true;
        }
        return false;
    }

    public boolean onMemberCreated(Member member) {
        currentState = new BoatClubMember();
        currentState.setMember(member);
        registerSavedState(currentState);
        return true;
    }


    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param id Id of a member.
     */
    public boolean onMemberDeleted(String id) {
        if (removeFromInstanceState(getStateById(id))) {
            return true;
        }
        return false;
    }

    /**
     * Receives updates and registers boat info with controller.
     *
     * @param boat Boat.
     */
    public boolean onBoatCreated(Boat boat) {
        if (currentState.getBoats().contains(boat)) {
            return false;
        } else {
            currentState.getBoats().add(boat);
        }
        registerSavedState(currentState);
        return true;
    }

    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param index Index of boat.
     */
    public boolean onBoatDeleted(int index) {
        if (index < currentState.getBoats().size()) {
            currentState.getBoats().remove(index);
            registerSavedState(currentState);
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
        if (index < currentState.getBoats().size()) {
            currentState.getBoats();
            registerSavedState(currentState);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Registers the current state with the base controller.
     */
    public void registerSavedState(BoatClubMember state) {
        super.addToInstanceState(state);
    }

    /**
     * Queries DB for Saved State. Sets local state if found and returns true.
     *
     * @param id Member id.
     * @return True/false
     */
    public boolean hasIDSavedState(String id) {
        this.currentState = super.getStateById(id);

        return currentState != null;
    }

    /**
     * @return saved Returns saved state's current member.
     */
    public Member getMember() {
        if (currentState.hasMember()) {
            return currentState.getMember();
        }
        throw new NullPointerException("No member found");
    }

    /**
     * @return Returns boats of current state.
     */
    public ArrayList<Boat> getBoats() {
        if (currentState.hasBoats()) {
            return currentState.getBoats();
        }
        throw new NoSuchElementException("There was an error while retrieving your boats.");
    }

    public ArrayList<BoatClubMember> getStates() {
        return super.getStates();
    }
}