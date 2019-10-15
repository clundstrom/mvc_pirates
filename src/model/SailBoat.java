package model;

public class SailBoat extends Boat{

    public SailBoat(String name, String model, int boatLength) {
        super.length = boatLength;
        super.name = name;
        super.modelName = model;
    }

    public SailBoat(){}
    
}