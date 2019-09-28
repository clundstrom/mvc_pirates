package model;

import java.util.ArrayList;

public interface IViewObserver {

    void onMemberUpdated(String[] args);
    void onMemberDeleted(String id);
    void onBoatUpdated(String[] boat);

}
