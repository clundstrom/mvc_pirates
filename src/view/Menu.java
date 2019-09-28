package view;
import model.IViewObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static final String ERR_INVALID_INPUT = "Invalid input";
    Scanner sc;
    ArrayList<IViewObserver> mSubscribers;

    public Menu() {
        sc = new Scanner(System.in);
        mSubscribers = new ArrayList<>();
    }

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
            "1. Name.",
            "2. Social security number.",
            "3. Back."
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
                    changeMember();
                    break;
                case '3':
                    // exit
                    break;
                case '4':
                    break;
                case '5':
                    break;
                default:
                    System.out.println(ERR_INVALID_INPUT);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] register() {
        clearConsole();
        String[] info = new String[2];
        System.out.println("Press \'r\' to go back");
        info[0] = requireInput("Please enter your name: ");
        if(info[0].equals("r"))
            presentation();
        else
            info[1] = requireInput("Social security number: ");

        notifySubscribers(info);
        return info;
    }

    public String requireInput(String question) {
        System.out.print(question);
        return this.sc.next();
    }


    /**
     * Changes a members information.
     *
     * @return
     */
    public void changeMember() {
        System.out.println("What would you like to change?\n");
        for(String s: changeMemberActions){
            System.out.println(s);
        }
        String answer = requireInput("");


        String[] newInfo = new String[2];
        switch (answer) {
            case "1":
                newInfo[0] = requireInput("Please enter your name: ");
                break;
            case "2":
                newInfo[1] = requireInput("Please enter your social security number: ");
                break;
            case "3":
                clearConsole();
                presentation();
                break;
            default:
                System.out.println(ERR_INVALID_INPUT);
                break;
        }

        notifySubscribers(newInfo);

    }

    private void notifySubscribers(String[] info) {
        for(IViewObserver sub : mSubscribers){
            sub.onMemberUpdated(info);
        }
    }


    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println("\n\n");
        }

    }

}
