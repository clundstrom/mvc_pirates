package view;

import controller.RegisterController;

public class VerboseView extends BaseView {

    public VerboseView(RegisterController controller) {
        super(controller);
    }


    /**
     * Responsible for printing detailed information about each member.
     */
    public void verboseViewList() {
        if(controller.getStates().size() < 1){
            System.out.println("No entries found.");
        }
        else {
            clearConsole();
            for(int i = 0; i < controller.getStates().size(); i++ ) {
                System.out.println("\nMember number: " + (i + 1));
                System.out.println("    Name: " + controller.getStates().get(i).getMember().getName());
                System.out.println("    Personal number: " + controller.getStates().get(i).getMember().getPersonalNumber());
                System.out.println("    Id: " + controller.getStates().get(i).getMember().getId());
                listBoats(controller, i);
            }
        }
    }

    /**
     *  Prints out a list of boats.
     *
     * @param controller Controller which is used to fetch the current boats from the correct state.
     * @param index index of BoatClubMember.
     */
    private void listBoats(RegisterController controller, int index){
        for(int j=0; j < controller.getStates().get(index).getBoats().size(); j++){
            System.out.println("    Boat number: " + (j + 1));
            System.out.println("        Boat Type: " +  controller.getStates().get(index).getBoats().get(j).getClass().getSimpleName());
            System.out.println("        Boat name: " +  controller.getStates().get(index).getBoats().get(j).getName());
            System.out.println("        Boat model: " +  controller.getStates().get(index).getBoats().get(j).getModelName());
            System.out.println("        Boat length: " +  controller.getStates().get(index).getBoats().get(j).getLength());

        }
    }
}