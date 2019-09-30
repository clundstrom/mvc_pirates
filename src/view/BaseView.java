package view;

import controller.RegisterController;
import model.IViewObserver;
import model.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * BaseView to which provides the most basic functions and a template for inheriting classes.
 */
public abstract class BaseView {

    protected final String ERR_INVALID_INPUT = "Invalid input";
    protected Scanner sc;
    protected ArrayList<IViewObserver> mSubscribers;
    protected RegisterController controller;

    public BaseView(RegisterController controller) {
        this.controller = controller;
        sc = new Scanner(System.in);
    }

    /**
     * Directs the user through the menu using their input.
     */
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

    /**
     * @param question A question ask the user.
     * @return The next input of User.
     */
    protected String requireInput(String question) {
        System.out.print(question);
        return this.sc.next();
    }

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
     * Adds a subscriber to the view.
     *
     * @param subscriber Subscriber who listens to information from the View.
     */
    public void addSubscriber(IViewObserver subscriber) {
        if (mSubscribers == null) {
            mSubscribers = new ArrayList<>();
        }
        mSubscribers.add(subscriber);
    }

    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param member
     */
    protected void notifyMemberChanged(Member member) {
        for (IViewObserver sub : mSubscribers) {
            sub.onMemberUpdated(member);
        }
    }

    protected void notifyMemberDeleted(String id) {
        for (IViewObserver sub : mSubscribers) {
            sub.onMemberDeleted(id);
        }
    }

    /**
     * Asks the controller if a specified member state exists in the database.
     * @return
     */
    protected boolean isMemberVerified() {
        return controller.hasIDSavedState(requireInput("Please enter your member ID: "));
    }
}
