package view;

import controller.RegisterController;


public class ListBoatView extends BaseView {
    public ListBoatView(RegisterController controller) {
        super(controller);
    }

    public void listBoats(){
        for(int i=0; i < controller.getBoats().size(); i++){
            System.out.println(i + ". " +  controller.getBoats().get(i).getName());
        }
    }
}
