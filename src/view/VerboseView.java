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
        if(controller.getRegistry().size() < 1){
            System.out.println("No entries found.");
        }
        else {
            clearConsole();
            for(int i = 0; i < controller.getRegistry().size(); i++ ) {
                System.out.println("\nMember number: " + (i + 1));
                System.out.println("    Name: " + controller.getRegistry().get(i).getMember().getName());
                System.out.println("    Personal number: " + controller.getRegistry().get(i).getMember().getPersonalNumber());
                System.out.println("    Id: " + controller.getRegistry().get(i).getMember().getId());
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
        for(int j = 0; j < controller.getRegistry().get(index).getBoats().size(); j++){
            System.out.println("    Boat number: " + (j + 1));
            System.out.println("        Boat Type: " +  controller.getRegistry().get(index).getBoats().get(j).getClass().getSimpleName());
            System.out.println("        Boat name: " +  controller.getRegistry().get(index).getBoats().get(j).getName());
            System.out.println("        Boat model: " +  controller.getRegistry().get(index).getBoats().get(j).getModelName());
            System.out.println("        Boat length: " +  controller.getRegistry().get(index).getBoats().get(j).getLength());

        }
    }
}