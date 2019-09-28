package controller;

import model.Boat;
import model.IViewObserver;
import model.Member;
import model.SavedState;

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
            if (updatedMember.getName().length() != 0) {
                this.currentState.getMember().setName(updatedMember.getName());
            }

            if (updatedMember.getPersonalNumber().length() != 0) {
                this.currentState.getMember().setPersonalNumber(updatedMember.getPersonalNumber());
            }
        } else {
            this.currentState.setMember(updatedMember);
            System.out.println("Member successfully registered.\nPlease save your unique id in a secure location: " + updatedMember.getId());
        }
        registerSavedState(this.currentState);
    }

    @Override
    public void onMemberDeleted(String id) {
        removeFromInstanceState(getStateById(id));
        System.out.println("Member successfully deleted.");
    }

    /**
     * Receives updates and registers boat info with controller.
     *
     * @param boat Boat.
     */

    @Override
    public void onBoatCreated(Boat boat) {

    }

    @Override
    public void onBoatDeleted(String name) {

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
    public boolean hasLocalSaveState(String id) {
        this.currentState = super.getStateById(id);

        return currentState == null;
    }
}