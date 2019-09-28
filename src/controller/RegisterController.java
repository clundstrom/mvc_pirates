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
     * Receives updates and registers member info with controller.
     *
     * @param args
     */
    @Override
    public void onMemberUpdated(String[] args) {
        if(args.length > 2 || args == null) throw new IndexOutOfBoundsException();

        if(args[0].length() != 0){
            this.currentState.getMember().setName(args[0]);
        }

        if(args[1].length() != 0){
            this.currentState.getMember().setPersonalNumber(args[1]);
        }
        registerSavedState(this.currentState);
    }

    /**
     * Receives updates and registers boat info with controller.
     *
     * @param boats Boats.
     */
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

    /**
     * Tries to fetch a local save state.
     * @param id Member id.
     * @return True/false
     */
    public boolean getLocalSaveState(String id){
        try {
            this.currentState = super.getStateById(id);
            return true;
        }
        catch (NoSuchElementException e){
            System.out.println("Could not find a member with that ID.");
        }
        return false;
    }
}