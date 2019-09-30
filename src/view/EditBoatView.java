package view;

import controller.RegisterController;
import model.*;

public class EditBoatView extends BaseView {

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
        addSubscriber(controller);
    }

    public void deleteBoat(){
        welcomeMessage("Which boat would you like to delete? ");
        new ListBoatView(controller).listBoats();
        String answer = requireInput("");
        int index = Integer.parseInt(answer);

        if(controller.getBoats().get(index) != null){
            notifyBoatDeleted(controller.getBoats().get(index).getName());
        }
        else {
            System.out.println("Invalid range.");
        }


    }

    public void addBoat() {
        super.clearConsole();
        welcomeMessage("What typ of boat do you want to register: ");
        presentActions(presentActions);
        String anwser = requireInput("");

        Boat boat;
        String name;
        String model;
        int length;

        switch (anwser) {
            case "1":
                name = requireInput("What is the name of the boat?: ");
                model = requireInput("What model is it?: ");
                length = Integer.parseInt(requireInput("How long is it? "));
                boat = new MotorBoat(name, model, length);
                notifyBoatChanged(boat);
                break;
            case "2":
                name = requireInput("What is the name of the boat?: ");
                model = requireInput("What model is it?: ");
                length = Integer.parseInt(requireInput("How long is it? "));
                boat = new SailBoat(name, model, length);
                notifyBoatChanged(boat);
                break;
            case "3":
                name = requireInput("What is the name of the boat?: ");
                model = requireInput("What model is it?: ");
                length = Integer.parseInt(requireInput("How long is it? "));
                boat = new Kayak(name, model, length);
                notifyBoatChanged(boat);
                break;
            case "4":
                name = requireInput("What is the name of the boat?: ");
                model = requireInput("What model is it?: ");
                length = Integer.parseInt(requireInput("How long is it? "));
                boat = new Canoe(name, model, length);
                notifyBoatChanged(boat);
                break;
            case "5":
                name = requireInput("What is the name of the boat?: ");
                model = requireInput("What model is it?: ");
                length = Integer.parseInt(requireInput("How long is it? "));
                boat = new Other(name, model, length);
                notifyBoatChanged(boat);
            case "6":
                break;
            default:
                addBoat();
                break;
        }
    }


    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param boat
     */
    protected void notifyBoatChanged(Boat boat) {
        for (IViewObserver sub : mSubscribers) {
            sub.onBoatCreated(boat);
        }
    }

    protected void notifyBoatDeleted(String name){
        for (IViewObserver sub : mSubscribers) {
            sub.onBoatDeleted(name);
        }
    }


}
