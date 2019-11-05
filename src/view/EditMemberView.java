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
        clearConsole();
        System.out.println("Press \'r\' to go back");
        String answer = requireInput("Please enter your name: ");
        if (answer.equalsIgnoreCase("r")) {
            return;
        } else {
            Member member = new Member();
            member.setName(answer);
            member.setPersonalNumber(requireInput("Social security number: "));
            isMemberCreated(member);
        }

    }

    /**
     * Change member view. Updates a local object and Notifies controller.
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
                updatedMember.setPersonalNumber("");
                isMemberChanged(updatedMember);
                break;
            case "2":
                updatedMember.setName("");
                updatedMember.setPersonalNumber(requireInput("Please enter your social security number: "));
                isMemberChanged(updatedMember);
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
        isMemberDeleted(id);
    }

    /**
     * Prints specific member information.
     */
    public void viewMember() {
        clearConsole();
        System.out.println("ID: " + controller.getMember().getId());
        System.out.println("Name: " + controller.getMember().getName());
        System.out.println("Social security number: " + controller.getMember().getPersonalNumber());

    }

    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param member
     */
    private void isMemberChanged(Member member) {
        if(controller.onMemberUpdated(member)){
            System.out.println("Profile updated.");
        }
    }

    private void isMemberDeleted(String id) {
        if(controller.onMemberDeleted(id)){
            System.out.println("Member successfully deleted.");
        }
    }

    private void isMemberCreated(Member member){
        if(controller.onMemberCreated(member)){
            System.out.println("Member successfully registered.\nPlease save your unique id in a secure location: " + member.getId());
        }
    }

}
