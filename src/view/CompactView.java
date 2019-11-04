package view;

import controller.RegisterController;

public class CompactView extends BaseView {

    public CompactView(RegisterController controller) {
        super(controller);
    }

    public void compactViewList() {
        if(controller.getRegistry().size() < 1){
            System.out.println("No entries found.");
        }
        else {
            clearConsole();
            for(int i = 0; i < controller.getRegistry().size(); i++ ) {
                System.out.println("Member number: " + (i + 1));
                System.out.println("    Name: " + controller.getRegistry().get(i).getMember().getName());
                System.out.println("    Id: " + controller.getRegistry().get(i).getMember().getId());
                System.out.println("    Number of boats: " + controller.getRegistry().get(i).getBoats().size());
            }
        }
    }
}
