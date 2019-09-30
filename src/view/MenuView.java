package view;

import controller.RegisterController;

import java.io.IOException;

/**
 * The view which handles the main menu.
 */
public class MenuView extends BaseView {


    private String[] presentActions = {
            "1. Register member.",
            "2. Change a member.",
            "3. Delete a member.",
            "4. View member.",
            "5. Compact list members.",
            "6. Exit"};


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
                    if (isMemberVerified())
                        new EditMemberView(controller).changeMember();
                    break;
                case '3':
                    new EditMemberView(controller).deleteMember();
                    break;
                case '4':
                    if (isMemberVerified())
                        new MemberInfoView(controller).viewMember();
                    break;
                case '5':
                    new CompactView(controller).compactViewList();
                    break;
                case '6':
                    System.out.println("Exiting..");
                    System.exit(0);
                    break;
                default:
                    System.err.println(ERR_INVALID_INPUT);
                    break;
            }
            onViewInit();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
