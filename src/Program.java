import controller.RegisterController;
import view.MenuView;

public class Program {

    
    public static void main(String[] args) {
        RegisterController registerController = new RegisterController();

        MenuView menu = new MenuView(registerController);
        menu.addSubscriber(registerController);
        menu.onViewInit();
    }

}

