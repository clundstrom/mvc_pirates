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
 *
 */
public class RegisterController extends BaseController implements IViewObserver {


    private SavedState currentState;

    public RegisterController() {
        currentState = new SavedState();
    }


    /**
     * Receives updates and sets
     *
     * @param args
     */
    @Override
    public void onMemberUpdated(String[] args) {
        this.currentState.setMember(new Member(args[0], args[1]));
        registerSavedState(this.currentState);
    }

    @Override
    public void onBoatUpdated(ArrayList<Boat> boats) {
        this.currentState.setBoats(boats);
    }

    /**
     * Registers the current state with the base controller.
     */
    public void registerSavedState(SavedState state){
        super.addToInstanceState(state);
    }

    public void getLocalSaveState(String id){
        try {
            this.currentState = super.getStateById(id);
        }
        catch (NoSuchElementException e){
            System.out.println("Could not find a member with that ID.");
        }
    }
}