package view;

import controller.RegisterController;
import model.IViewObserver;
import model.MotorBoat;
import model.Boat;

public class EditBoatView extends MenuView {

    private String[] presentActions = {
        "1. MotorBoat",
        "2. SailBoat",
        "3. Kayak",
        "4. Canoe",
        "5. Other",
        "6. back"
};

    public EditBoatView(RegisterController controller) {
        super(controller);
    }

    public void registerMotorboat() {
        System.out.println("Press \'r\' to go back");
        String boatName = requireInput("Please enter name of boat: ");
        if (boatName.equalsIgnoreCase("r")) {
            return;
        } else {
            String boatModel = requireInput("Please enter boat modal: ");
            int boatLength = Integer.parseInt(requireInput("Please enter boat length: "));
            Boat boat = new MotorBoat(boatName, boatModel, boatLength);
            notifyBoatSubscribers(boat);
        }
    }

    public void addBoat() {
        super.clearConsole();
        presentActions(presentActions);
        String anwser = requireInput("\nWhat typ of boat do you want to register:");

        switch(anwser) {
            case"1":
            registerMotorboat();
                break;
            case"2":
                //Add SailBoat
                break;
            case"3":
                //Add Kayak
                break;
            case"4":
                //Add Canoe
                break;
            case"5":
                //Add Other
            case"6":
                super.presentActions(presentActions);
                break;       
        }

        
    }






    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param boat
     */
    protected void notifyBoatSubscribers(Boat boat) {
        for (IViewObserver sub : mSubscribers) {
            sub.onBoatCreated(boat);
        }
    }



}
