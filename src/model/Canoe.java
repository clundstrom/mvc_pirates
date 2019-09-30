package model;

public class Canoe extends Boat {

    public Canoe(String name, String model, int boatLength) {
        super.length = boatLength;
        super.name = name;
        super.modelName = model;
    }

    public Canoe(){}

    @Override
    public int getLength() {
        return super.getLength();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);
    }

    @Override
    public String getModelName() {
        return super.getModelName();
    }

    @Override
    public void setModelName(String modelName) {
        super.setModelName(modelName);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
