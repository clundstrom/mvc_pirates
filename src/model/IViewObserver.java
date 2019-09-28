package model;

import java.util.ArrayList;

public interface IViewObserver {

    void onMemberUpdated(String[] args);
    void onBoatUpdated(ArrayList<Boat> args);

}
