package model;


public class Member {

    private String name;
    private String id = "";
    private String personalNumber = "";


    public Member(String nm, String persNum) {
        name = nm;
        personalNumber = persNum;
    }

    public Member(){
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
    public void setId(String uniqueId) {
        id = uniqueId;
    }
}



