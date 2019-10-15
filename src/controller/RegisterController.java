package controller;

import model.Boat;
import model.IViewObserver;
import model.Member;
import model.SavedState;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Controller which handles CRUD operations on the
 * registry of Members and Boats.
 */
public class RegisterController extends BaseController implements IViewObserver {


    private SavedState currentState;

    public RegisterController() {
        currentState = new SavedState();
    }

    /**
     * Provides the ability to initialize a controller with a custom saved state.
     * @param savedState
     */
    public RegisterController(SavedState savedState) {
        currentState = savedState;
    }


    /**
     * Receives updates and registers member info with controller.
     *
     * @param updatedMember Member which is updated.
     */
    @Override
    public void onMemberUpdated(Member updatedMember) {
        // Check if member exists
        if (currentState.hasMember()) {
            if (!updatedMember.getName().isEmpty()) {
                this.currentState.getMember().setName(updatedMember.getName());
            }

            if (!updatedMember.getPersonalNumber().isEmpty()) {
                this.currentState.getMember().setPersonalNumber(updatedMember.getPersonalNumber());
            }
            registerSavedState(this.currentState);
            System.out.println("Profile updated.");
        }

    }

    @Override
    public void onMemberCreated(Member member) {
        currentState = new SavedState();
        currentState.setMember(member);
        registerSavedState(currentState);
        System.out.println("Member successfully registered.\nPlease save your unique id in a secure location: " + member.getId());
    }


    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param id Id of a member.
     */
    @Override
    public void onMemberDeleted(String id) {
        if (removeFromInstanceState(getStateById(id))) {
            System.out.println("Member successfully deleted.");
        }
    }

    /**
     * Receives updates and registers boat info with controller.
     *
     * @param boat Boat.
     */
    @Override
    public void onBoatCreated(Boat boat) {
        if (currentState.getBoats().contains(boat)) {
            System.out.println("Cannot add duplicate boats.");
        } else {
            currentState.getBoats().add(boat);
        }
        registerSavedState(currentState);
    }

    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param index Index of boat.
     */
    @Override
    public void onBoatDeleted(int index) {
        if (index < currentState.getBoats().size()) {
            currentState.getBoats().remove(index);
            registerSavedState(currentState);
            System.out.println("Boat successfully deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }


    /**
     * Updates corresponding boat.
     * @param index Index of boat to update.
     */
    @Override
    public void onBoatUpdate(int index) {
        if (index < currentState.getBoats().size()) {
            currentState.getBoats();
            registerSavedState(currentState);
            System.out.println("Boat successfully updated.");
        } else {
            System.out.println("Invalid index.");
        }

    }

    /**
     * Registers the current state with the base controller.
     */
    public void registerSavedState(SavedState state) {
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
     *
     * @return saved Returns saved state's current member.
     */
    public Member getMember() {
        if (!currentState.hasMember()) {
            System.out.println("No member found.");
            return null;
        }
        return currentState.getMember();
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

    public ArrayList<SavedState> getStates() {
        return super.getStates();
    }
}