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

    private String[] presentChangeBoatActions = {
        "1. Boat name",
        "2. Boat model",
        "3. Boat length",
        "4. back"
};

    public EditBoatView(RegisterController controller) {
        super(controller);
    }

    /**
     * Provides a list from which the user can choose to delete a boat.
     */
    public void deleteBoat() {
        if (!controller.getBoats().isEmpty()) {
            welcomeMessage("Which boat would you like to delete? ");
            new ListBoatView(controller).listBoats();
            String answer = requireInput("");
            try{
                int index = Integer.parseInt(answer);
                notifyBoatDeleted(index);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input.");
            }

        } else {
            System.out.println("You have no boats registered.");
        }
    }

    /**
     * Updates a boat chosen from a list.
     */
    public void updateBoat(){
        if (!controller.getBoats().isEmpty()) {
            welcomeMessage("\nWhich boat would you like to Update? ");
            new ListBoatView(controller).listBoats();

            try{
                int index = Integer.parseInt(requireInput(""));
                welcomeMessage("\nWhat do you like to change:");
                presentActions(presentChangeBoatActions);
                String answer = requireInput("");

                switch(answer){
                    case"1":
                        String changeName = requireInput("Enter new name: ");
                        controller.getBoats().get(index).setName(changeName);
                        notifyBoatUpdated(index);
                        break;
                    case"2":
                        String changeModel = requireInput("Enter new model: ");
                        controller.getBoats().get(index).setModelName(changeModel);
                        notifyBoatUpdated(index);
                        break;
                    case"3":
                        int changeLength = Integer.parseInt(requireInput("Enter new length: "));
                        controller.getBoats().get(index).setLength(changeLength);
                        notifyBoatUpdated(index);
                        break;
                    case"4":
                        break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input.");
            }
        } else {
            System.out.println("You have no boats registered.");
        }
    }

    /**
     * Creates a local boat which is updated with information and sent to controller
     * for registration.
     * @param boat
     */
    public void addBoat(Boat boat) {
        super.clearConsole();
        welcomeMessage("What typ of boat do you want to register: ");
        presentActions(presentActions);

        try{
            String answer = requireInput("");
            String name;
            String model;
            int length;

            switch (answer) {
                case "1":
                    name = requireInput("What is the name of the boat?: ");
                    model = requireInput("What model is it?: ");
                    length = Integer.parseInt(requireInput("How long is it (m)? "));
                    boat = new MotorBoat(name, model, length);
                    notifyBoatChanged(boat);
                    break;
                case "2":
                    name = requireInput("What is the name of the boat?: ");
                    model = requireInput("What model is it?: ");
                    length = Integer.parseInt(requireInput("How long is it? (m) "));
                    boat = new SailBoat(name, model, length);
                    notifyBoatChanged(boat);
                    break;
                case "3":
                    name = requireInput("What is the name of the boat?: ");
                    model = requireInput("What model is it?: ");
                    length = Integer.parseInt(requireInput("How long is it? (m) "));
                    boat = new Kayak(name, model, length);
                    notifyBoatChanged(boat);
                    break;
                case "4":
                    name = requireInput("What is the name of the boat?: ");
                    model = requireInput("What model is it?: ");
                    length = Integer.parseInt(requireInput("How long is it? (m) "));
                    boat = new Canoe(name, model, length);
                    notifyBoatChanged(boat);
                    break;
                case "5":
                    name = requireInput("What is the name of the boat?: ");
                    model = requireInput("What model is it?: ");
                    length = Integer.parseInt(requireInput("How long is it? (m) "));
                    boat = new Other(name, model, length);
                    notifyBoatChanged(boat);
                case "6":
                    break;
                default:
                    addBoat(new Other());
                    break;
            }
        }
        catch (NumberFormatException e){
            System.out.println("Invalid input.");
        }
    }


    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param boat
     */
    protected void notifyBoatChanged(Boat boat) {
        if(!controller.onBoatCreated(boat)){
            System.out.println("Cannot add duplicate boats.");
        }
    }

    protected void notifyBoatDeleted(int index) {
            if(controller.onBoatDeleted(index)){
                System.out.println("Boat successfully deleted.");
            }
            else{
                System.out.println("Invalid index.");
            }
    }

    protected void notifyBoatUpdated(int index) {
            if(controller.onBoatUpdated(index)){
                System.out.println("Boat successfully updated.");
            }
            else {
                System.out.println("Invalid index.");
            }
    }


}
