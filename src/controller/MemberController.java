package controller;

import model.IViewObserver;
import model.Member;
import view.Menu;

import java.util.ArrayList;


public class MemberController implements IViewObserver {

    private ArrayList<Member> mList;
    private Menu currentView;


    public MemberController() {
    }


    @Override
    public void viewUpdated(String[] args) {
        Member member = new Member(args[0], args[1]);
        System.out.println("Success.");
        System.out.println(member.getName() + member.getPersonalNumber() + member.getId());
    }
}