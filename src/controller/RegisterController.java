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
            registerSavedState(this.currentMember);
            return true;
        }
        return false;
    }

    public boolean onMemberCreated(Member member) {
        currentMember = new BoatClubMember();
        currentMember.setMember(member);


        registerSavedState(currentMember);
        return true;
    }


    /**
     * Receives deletion notification and calls BaseController for removal.
     *
     * @param id Id of a member.
     */
    public boolean onMemberDeleted(String id) {
        if (removeFromInstanceState(getMemberById(id))) {
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
        if (currentMember.getBoats().contains(boat)) {
            return false;
        } else {
            currentMember.getBoats().add(boat);
        }
        registerSavedState(currentMember);
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
            registerSavedState(currentMember);
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
            registerSavedState(currentMember);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Registers the current state with the base controller.
     */
    public void registerSavedState(BoatClubMember state) {
        super.addToRegistry(state);
    }

    /**
     * Queries DB for Saved State. Sets local state if found and returns true.
     *
     * @param id Member id.
     * @return True/false
     */
    public boolean hasMemberId(String id) {
        this.currentMember = super.getMemberById(id);

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

    public String uniqueId() {
        while (true) {
            String temp = createId();
            if (!hasMemberId(temp)) {
                return temp;
            }
        }
    }

    public ArrayList<BoatClubMember> getRegistry() {
        return super.getRegistry();
    }
}