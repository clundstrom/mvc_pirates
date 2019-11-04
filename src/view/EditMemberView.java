package view;

import controller.RegisterController;
import model.Member;
import model.Other;

/**
 * The view handles editing of members.
 */
public class EditMemberView extends BaseView {


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
            member.setId(controller.uniqueId());
            isMemberCreated(member);
        }

    }

    /**
     * Change member view. Updates a local object and Notifies controller.
     *
     * @return
     */
    public void changeMember(Member updatedmember) {
        presentActions(presentActions);

        String answer = requireInput("");

        Member updatedMember = updatedmember;
        switch (answer) {
            case "1":
                updatedMember.setName(requireInput("Please enter your name: "));
                updatedMember.setPersonalNumber("");
                isMemberChanged(updatedMember);
                break;
            case "2":
                updatedMember.setName("");
                updatedMember.setPersonalNumber(requireInput("Please enter your social security number: "));
                isMemberChanged(updatedMember);
                break;
            case "3":
                new EditBoatView(controller).addBoat(new Other());
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
        changeMember(new Member());
    }

    /**
     * Delete member view. Notifies controller.
     */
    public void deleteMember() {
        String id = requireInput("Which member would you like to delete? Enter an id: ");
        isMemberDeleted(id);
    }

}
