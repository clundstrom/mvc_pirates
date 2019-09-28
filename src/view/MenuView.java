package view;

import controller.RegisterController;
import model.IViewObserver;

import java.io.IOException;
import java.util.ArrayList;

public class MenuView extends BaseView {


    private String[] presentActions = {
            "1. Register member.",
            "2. Change a member.",
            "3. Delete a member.",
            "4. MenuView member.",
            "5. List members."};


    public MenuView(RegisterController controller) {
        super(controller);
    }

    @Override
    public void onViewInit() {
        welcomeMessage("Welcome to the Jolly Pirates.\n\nWhat would you like to do? (press q to exit)");
        presentActions(presentActions);
        getInputAction();
    }

    @Override
    protected void welcomeMessage(String message) {
        super.welcomeMessage(message);
    }

    @Override
    protected void presentActions(String[] actions) {
        super.presentActions(actions);
    }

    @Override
    protected void getInputAction() {
        try {
            int key = System.in.read();
            switch (key) {
                case 'q':
                    System.out.println("Exited..");
                    break;
                case '1':
                    new EditMemberView(controller).register();
                    break;
                case '2':
                    new EditMemberView(controller).changeMember();
                    break;
                case '3':
                    // exit
                    break;
                case '4':
                    break;
                case '5':
                    break;
                default:
                    System.err.println(ERR_INVALID_INPUT);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
