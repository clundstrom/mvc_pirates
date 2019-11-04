package model;

import java.util.Random;

public class Member {

    private String name;
    private String id = "";
    private String personalNumber = "";


    public Member(String nm, String persNum) {
        name = nm;
        personalNumber = persNum;
        setId();
    }

    public Member(){
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

    /**
     * Sets a 6 digit random id for member
     */
    public void setId() {
        Random rand = new Random();
        int maxIdLength = 6;
        String temp = "";
        for(int i = 0; i < maxIdLength; i++) {
            temp += Integer.toString(rand.nextInt(10));

        }
        this.id = temp;
    }
}



