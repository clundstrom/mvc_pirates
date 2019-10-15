package model;

public interface IViewObserver {

    void onMemberUpdated(Member member);
    void onMemberCreated(Member member);
    void onMemberDeleted(String id);
    void onBoatUpdated(int index);
    void onBoatDeleted(int index);
    void onBoatCreated(Boat boat);

}
