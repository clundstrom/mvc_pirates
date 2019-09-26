package model;

import java.util.UUID;

public class Member {

    
    private String name;
    private String id;
    private String personalNumber;


    public Member(String nm, String persNum) {
        name = nm;
        personalNumber = persNum;
        id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPersonalNumber(){
        return personalNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    private void setId(){
       
    }
}



