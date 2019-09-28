package controller;

import model.IViewObserver;
import model.Member;
import model.SavedState;

import java.util.NoSuchElementException;


/**
 * Controller which handles CRUD operations on the
 * registry of Members and Boats.
 *
 */
public class RegisterController extends BaseController implements IViewObserver {


    private SavedState currentState;

    public RegisterController() {
        currentState = new SavedState();
    }


    /**
     * Receives updates and registers member info with controller.
     *
     * @param args
     */
    @Override
    public void onMemberUpdated(String[] args) {
        // Check if member exists
        if(currentState.hasMember()){
            if(args[0].length() != 0){
                this.currentState.getMember().setName(args[0]);
            }

            if(args[1].length() != 0){
                this.currentState.getMember().setPersonalNumber(args[1]);
            }
        }
        else {
            Member member = (new Member(args[0], args[1]));
            this.currentState.setMember(new Member(args[0], args[1]));
            System.out.println("Member successfully registered.\nPlease save your unique id in a secure location: " + member.getId());
        }
        registerSavedState(this.currentState);
    }

    @Override
    public void onMemberDeleted(String id) {
        removeFromInstanceState(getStateById(id));
    }

    /**
     * Receives updates and registers boat info with controller.
     *
     * @param boatInformation Boat.
     */
    @Override
    public void onBoatUpdated(String[] boatInformation) {
    }

    /**
     * Registers the current state with the base controller.
     */
    public void registerSavedState(SavedState state){
        super.addToInstanceState(state);
    }

    /**
     * Queries DB for Saved State. Sets local state if found and returns true.
     * @param id Member id.
     * @return True/false
     */
    public boolean hasLocalSaveState(String id){
        try {
            this.currentState = super.getStateById(id);
            return true;
        }
        catch (NoSuchElementException e){
            System.out.println("Could not find a saved state with that ID.");
        }
        return false;
    }
}