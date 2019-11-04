package model;

import java.util.ArrayList;


/**
 * Object to represent a the current state of a Member and its boats.
 *
 */
public class BoatClubMember {

    private Member member;
    private ArrayList<Boat> boats;

    public BoatClubMember() {
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

    public boolean hasMember(){
        return member.getName() != null;
    }

    public boolean hasBoats(){
        return boats != null;
    }
}
