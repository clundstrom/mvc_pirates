package view;

import controller.RegisterController;
import model.Member;


/**
 * The view handles editing of members.
 */
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


    /**
     * Register member view. Notifies controller with new information.
     */
    public void register() {
        super.clearConsole();
        System.out.println("Press \'r\' to go back");
        String answer = requireInput("Please enter your name: ");
        if (answer.equalsIgnoreCase("r")){
            return;
        }
        else{
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
        promptMemberId();
        presentActions(presentActions);

        String answer = requireInput("");

        Member updatedMember = new Member();
        switch (answer) {
            case "1":
                updatedMember.setName(requireInput("Please enter your name: "));
                break;
            case "2":
                updatedMember.setPersonalNumber(requireInput("Please enter your social security number: "));
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

        notifyMemberChanged(updatedMember);
    }

    /**
     * Delete member view. Notifies controller.
     */
    public void deleteMember(){
        String id = requireInput("Which member would you like to delete? Enter an id: ");
        notifyMemberDeleted(id);
    }

    private void promptMemberId() {
        if (controller.hasLocalSaveState(requireInput("Please enter your member ID: "))) {
        }
    }
}
