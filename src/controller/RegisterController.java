package controller;

import model.Boat;
import model.IViewObserver;
import model.Member;
import model.SavedState;

import java.sql.SQLOutput;
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
     * Receives updates and registers member info with controller.
     *
     * @param updatedMember Member which is updated.
     */
    @Override
    public void onMemberUpdated(Member updatedMember) {
        // Check if member exists
        if (currentState.hasMember()) {
            // The updated
            if (updatedMember.getName() != null) {
                this.currentState.getMember().setName(updatedMember.getName());
            }

            if (updatedMember.getPersonalNumber() != null) {
                this.currentState.getMember().setPersonalNumber(updatedMember.getPersonalNumber());
            }
        } else {
            this.currentState.setMember(updatedMember);
            System.out.println("Member successfully registered.\nPlease save your unique id in a secure location: " + updatedMember.getId());
        }
        registerSavedState(this.currentState);
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
     * @param name Name of boat.
     */
    @Override
    public void onBoatDeleted(String name) {
        for (Boat boat : currentState.getBoats()){
            if (boat.getName().equalsIgnoreCase(name)){
                int index = currentState.getBoats().indexOf(boat);
                currentState.getBoats().remove(index);
            }
        }
        registerSavedState(currentState);
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
     * Fetches the saved state's current member.
     * @return
     */
    public Member getMember(){
        if(currentState.getMember() == null){
            System.out.println("No member found.");
            return null;
        }
        return currentState.getMember();
    }
}