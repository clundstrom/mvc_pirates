package view;

import controller.RegisterController;

public class CompactView extends BaseView {

    public CompactView(RegisterController controller) {
        super(controller);
    }

    public void compactViewList() {
        if(controller.getStates().size() < 1){
            System.out.println("No entries found.");
        }
        else {
            clearConsole();
            for(int i = 0; i < controller.getStates().size(); i++ ) {
                System.out.println("Member number: " + (i + 1));
                System.out.println("    Name: " + controller.getStates().get(i).getMember().getName());
                System.out.println("    Id: " + controller.getStates().get(i).getMember().getId());
                System.out.println("    Number of boats: " + controller.getStates().get(i).getBoats().size());
            }
        }
    }
}
