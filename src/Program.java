import controller.BaseController;
import controller.RegisterController;
import view.MenuView;
import controller.BaseController;
import java.io.File;

public class Program {

    
    public static void main(String[] args) {
<<<<<<< HEAD
        BaseController bs = new BaseController(new File("src\\assets\\database.json"), null);
=======
        BaseController bs = new BaseController(new File("database.json"), null);
>>>>>>> 9a3211e6482fffe0af833959f1ef5cf0648ee2b2
        RegisterController registerController = new RegisterController();

        MenuView menu = new MenuView(registerController);
        menu.addSubscriber(registerController);
        menu.onViewInit();
    }

}

