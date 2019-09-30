package view;

import controller.RegisterController;
import model.SavedInstanceState;

public class CompactView extends BaseView {

    SavedInstanceState savedStates;

    public CompactView(RegisterController controller) {
        super(controller);
    }

    public void compactViewList() {
        for(int i = 0; i < savedStates.getSavedStates().size(); i++ ) {
            clearConsole();
            System.out.println("Member number: " + (i + 1));
            System.out.println("    Name: " + savedStates.getSavedStates().get(i).getMember().getName());
            System.out.println("    Id: " + savedStates.getSavedStates().get(i).getMember().getId());
            System.out.println("    Number of boats: " + savedStates.getSavedStates().get(i).getBoats().size());
        }
    }
    




    
}
