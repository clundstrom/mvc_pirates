package view;

import controller.RegisterController;
import model.Member;

import java.io.IOException;
import java.util.Scanner;

/**
 * BaseView to which provides the most basic functions and a template for inheriting classes.
 */
public abstract class BaseView {

    protected final String ERR_INVALID_INPUT = "Invalid input";
    protected Scanner sc;
    protected RegisterController controller;

    public BaseView(RegisterController controller) {
        this.controller = controller;
        sc = new Scanner(System.in);
    }


    /**
     * Overrideble behavior of getInputAction and provides navigation options for the user.
     */
    protected void getInputAction() {
        try {
            int key = System.in.read();
            switch (key) {
                case 'q':
                    break;
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param question A question ask the user.
     * @return The next input of User.
     */
    protected String requireInput(String question) {
        System.out.print(question);
        sc = new Scanner(System.in);
        return this.sc.nextLine();
    }

    /**
     * Clears the console.
     */
    protected static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println("\n\n");
        }
    }


    /**
     * Function to be called in childobjects when the view initializes.
     */
    public void onViewInit() {
    };

    protected void welcomeMessage(String message) {
        System.out.println(message);
    };

    protected void presentActions(String[] presentActions) {
        if (presentActions != null) {
            for (String msg : presentActions) {
                System.out.println(msg);
            }
        }
    }

    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param member
     */
    protected void isMemberChanged(Member member) {
        if(controller.onMemberUpdated(member)){
            System.out.println("Profile updated.");
        }
    }

    protected void isMemberDeleted(String id) {
        if(controller.onMemberDeleted(id)){
            System.out.println("Member successfully deleted.");
        }
    }

    protected void isMemberCreated(Member member){
        if(controller.onMemberCreated(member)){
            System.out.println("Member successfully registered.\nPlease save your unique id in a secure location: " + member.getId());
        }
    }

    /**
     * Asks the controller if a specified member state exists in the database.
     * @return
     */
    protected boolean isMemberVerified() {

        String input = requireInput("Please enter your member ID: ");

        if(!controller.hasMemberId(input)){
            System.out.println("Could not find a member with ID: " + input);
            return false;
        }

        return true;
    }
}
