import controller.BaseController;
import controller.RegisterController;
import view.MenuView;

public class Program {


    public static void main(String[] args) {
        BaseController bs = new BaseController();
        RegisterController registerController = new RegisterController();

        MenuView menu = new MenuView(registerController);
        menu.addSubscriber(registerController);
        menu.onViewInit();
    }

}

