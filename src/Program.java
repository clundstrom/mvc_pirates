import model.*;

import java.util.ArrayList;

public class Program {


    public static void main(String[] args) {

        ArrayList<Boat> boatList = new ArrayList<>();

        Boat boat = new Kayak("Greta", "BigPlay", 20);
        Boat boat1 = new Canoe("Babe", "Big", 10);

        boatList.add(boat);
        boatList.add(boat1);



    }

}

