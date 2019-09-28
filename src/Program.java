import controller.BaseController;
import controller.RegisterController;
import view.Menu;

public class Program {


    public static void main(String[] args) {
        BaseController bs = new BaseController();
        RegisterController registerController = new RegisterController();

        Menu menu = new Menu(registerController);
        menu.addSubscriber(registerController);
        menu.presentation();
    }

}

