package model;

import java.util.ArrayList;


/**
 * Object to represent a the current state of a Member and its boats.
 *
 */
public class SavedState {

    private Member member;
    private ArrayList<Boat> boats;


    public SavedState(Member member, ArrayList<Boat> boats) {
        this.member = member;
        this.boats = boats;
    }

    public SavedState() {
        member = new Member();
        boats = new ArrayList<>();
    }

    public Member getMember() {
        if(member == null){
            member = new Member("", "");
        }
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ArrayList<Boat> getBoats() {
        if(boats == null){
            boats = new ArrayList<>();
        }
        return boats;
    }

    public void setBoats(ArrayList<Boat> boats) {
        this.boats = boats;
    }

    public boolean hasMember(){
        return member.getName() != null;
    }

    public boolean hasBoats(){
        return boats != null;
    }
}
