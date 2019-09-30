package model;

public interface IViewObserver {

    void onMemberUpdated(Member member);
    void onMemberDeleted(String id);
    void onBoatUpdate(int index);
    void onBoatDeleted(int index);
    void onBoatCreated(Boat boat);

}
