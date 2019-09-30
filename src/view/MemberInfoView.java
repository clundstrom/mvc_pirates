package view;

import controller.RegisterController;

public class MemberInfoView extends BaseView {


    public MemberInfoView(RegisterController controller) {
        super(controller);
    }

    public void viewMember() {
        clearConsole();
        System.out.println("ID: " + controller.getMember().getId());
        System.out.println("Name " + controller.getMember().getName());
        System.out.println("Social security number: " + controller.getMember().getPersonalNumber());

    }
}
