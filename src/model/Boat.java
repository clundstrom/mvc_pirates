package model;

public abstract class Boat {

    protected int length;
    protected String modelName;
    protected String name;

    public Boat(String name, String model, int boatLength){
        this.name = name;
        this.modelName = model;
        this.length = boatLength;
    }

    public Boat(){}


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



 
}