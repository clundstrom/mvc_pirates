package view;

import controller.RegisterController;
import model.Member;

/**
 * The view handles editing of members.
 */
public class EditMemberView extends BaseView {


    private boolean verified = false;

    private String[] presentActions = {
            "1. Change name.",
            "2. Change social security number.",
            "3. Add boat.",
            "4. Remove boat.",
            "5. Update boat",
            "6. Back."
    };


    public EditMemberView(RegisterController controller) {
        super(controller);
        super.addSubscriber(controller);
    }


    /**
     * Register member view. Notifies controller with new information.
     */
    public void register() {
        super.clearConsole();
        System.out.println("Press \'r\' to go back");
        String answer = requireInput("Please enter your name: ");
        if (answer.equalsIgnoreCase("r")) {
            return;
        } else {
            Member member = new Member();
            member.setName(answer);
            member.setPersonalNumber(requireInput("Social security number: "));
            notifyMemberChanged(member);
        }

    }

    /**
     * Change member view. Update LOCAL objects and Notifies controller.
     *
     * @return
     */
    public void changeMember() {
        presentActions(presentActions);

        String answer = requireInput("");

        Member updatedMember = new Member();
        switch (answer) {
            case "1":
                updatedMember.setName(requireInput("Please enter your name: "));
                notifyMemberChanged(updatedMember);
                break;
            case "2":
                updatedMember.setPersonalNumber(requireInput("Please enter your social security number: "));
                notifyMemberChanged(updatedMember);
                break;
            case "3":
                new EditBoatView(controller).addBoat();
                break;
            case "4":
                new EditBoatView(controller).deleteBoat();
                break;
            case"5":
                new EditBoatView(controller).updateBoat();
                break;
            case "6":
                clearConsole();
                return;
            default:
                System.err.println(ERR_INVALID_INPUT);
                break;
        }
        changeMember();
    }

    /**
     * Delete member view. Notifies controller.
     */
    public void deleteMember() {
        String id = requireInput("Which member would you like to delete? Enter an id: ");
        notifyMemberDeleted(id);
    }

}
