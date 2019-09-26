package model;

import java.util.Random;

public class Member {

    
    private String name;
    private String id = "";
    private String personalNumber;


    public Member(String nm, String persNum) {
        name = nm;
        personalNumber = persNum;
        setId();
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

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    private void setId() {
        Random rand = new Random();
        int idLenght = 6;
        for(int i = 0; i < idLenght; i++) {
            id += Integer.toString(rand.nextInt(10));
        }
    }
}



