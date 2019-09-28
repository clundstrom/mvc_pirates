package view;

import controller.RegisterController;
import model.IViewObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final String ERR_INVALID_INPUT = "Invalid input";
    private Scanner sc;
    private ArrayList<IViewObserver> mSubscribers;
    private RegisterController controller;

    public Menu(RegisterController controller) {
        this.controller = controller;
        sc = new Scanner(System.in);
        mSubscribers = new ArrayList<>();
    }

    /**
     * Adds a subscriber to the view.
     *
     * @param subscriber Subscriber who listens to information from the View.
     */
    public void addSubscriber(IViewObserver subscriber) {
        mSubscribers.add(subscriber);
    }

    String[] presentationActions = {
            "1. Register member.",
            "2. Change a member.",
            "3. Delete a member.",
            "4. Menu member.",
            "5. List members."};
    String[] changeMemberActions = {
            "1. Change name.",
            "2. Change social security number.",
            "3. Add boat.",
            "4. Remove boat.",
            "5. Back."
    };


    public void presentation() {
        System.out.println("Welcome to the Jolly Pirates.\n\nWhat would you like to do? (press q to exit)");
        for (String i : presentationActions) {
            System.out.println(i);
        }
        getInput();
    }

    public void getInput() {
        try {
            int key = System.in.read();
            switch (key) {
                case 'q':
                    System.out.println("Exited..");
                    break;
                case '1':
                    register();
                    break;
                case '2':
                    promptMemberId();
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

    public void register() {
        clearConsole();
        String[] info = new String[2];
        System.out.println("Press \'r\' to go back");
        info[0] = requireInput("Please enter your name: ");
        if (info[0].equals("r")){
            presentation();
        }

        else{
            info[1] = requireInput("Social security number: ");
        }
        notifySubscribers(info);
        changeMember();
    }

    public String requireInput(String question) {
        System.out.print(question);
        return this.sc.next();
    }


    public void promptMemberId() {
        if (controller.getLocalSaveState(requireInput("Please enter your member ID: "))) {
            changeMember();
        }
    }

    /**
     * Changes a members information.
     *
     * @return
     */
    public void changeMember() {
        System.out.println("Welcome! What would you like to do?");
        for (String s : changeMemberActions) {
            System.out.println(s);
        }
        String answer = requireInput("");


        String[] changedMemberInfo = new String[2];
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
                presentation();
                break;
            default:
                System.err.println(ERR_INVALID_INPUT);
                break;
        }

        notifySubscribers(changedMemberInfo);

    }

    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param info
     */
    private void notifySubscribers(String[] info) {
        for (IViewObserver sub : mSubscribers) {
            sub.onMemberUpdated(info);
        }
    }


    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println("\n\n");
        }

    }

}
