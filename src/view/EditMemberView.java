package view;

import controller.RegisterController;

public class EditMemberView extends BaseView {

    private String[] presentActions = {
            "1. Change name.",
            "2. Change social security number.",
            "3. Add boat.",
            "4. Remove boat.",
            "5. Back."
    };


    public EditMemberView(RegisterController controller) {
        super(controller);
        super.addSubscriber(controller);
    }

    public void register() {
        super.clearConsole();
        String[] info = new String[2];
        System.out.println("Press \'r\' to go back");
        info[0] = requireInput("Please enter your name: ");
        if (info[0].equals("r")){
            return;
        }
        else{
            info[1] = requireInput("Social security number: ");
        }
        notifyMemberSubscribers(info);
    }

    /**
     * Changes a members information.
     *
     * @return
     */
    public void changeMember() {
        promptMemberId();
        presentActions(presentActions);

        String answer = requireInput("");

        String[] changedMemberInfo = new String[2];
        String[] changedBoatInfo = new String[3];
        switch (answer) {
            case "1":
                changedMemberInfo[0] = requireInput("Please enter your name: ");
                changedMemberInfo[1] = "";
                break;
            case "2":
                changedMemberInfo[0] = "";
                changedMemberInfo[1] = requireInput("Please enter your social security number: ");
                break;
            case "3":

                // Add boat
                break;
            case "4":
                // Remove boat
                break;
            case "5":
                clearConsole();
                break;
            default:
                System.err.println(ERR_INVALID_INPUT);
                break;
        }

        notifyMemberSubscribers(changedMemberInfo);
    }

    private void promptMemberId() {
        if (controller.getLocalSaveState(requireInput("Please enter your member ID: "))) {
            welcomeMessage("Welcome! What would you like to do?");
        }
    }
}
