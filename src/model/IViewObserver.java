package model;

public interface IViewObserver {

    void onMemberUpdated(Member member);
    void onMemberDeleted(String id);
    void onBoatCreated(Boat boat);
    void onBoatDeleted(String name);

}
