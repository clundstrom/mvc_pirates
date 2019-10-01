import controller.BaseController;
import controller.RegisterController;
import view.MenuView;
import controller.BaseController;
import java.io.File;

public class Program {

    
    public static void main(String[] args) {
        BaseController bs = new BaseController(new File("database.json"), null);
        RegisterController registerController = new RegisterController();

        MenuView menu = new MenuView(registerController);
        menu.addSubscriber(registerController);
        menu.onViewInit();
    }

}

