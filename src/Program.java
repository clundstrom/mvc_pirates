import controller.RegisterController;
import view.MenuView;

import java.io.File;

public class Program {

    
    public static void main(String[] args) {
<<<<<<< HEAD
=======
        BaseController bs = new BaseController(new File("src\\assets\\database.json"), null);
>>>>>>> 231e6201b016d9d4ffcbbb1843314bc88892cb07
        RegisterController registerController = new RegisterController();

        MenuView menu = new MenuView(registerController);
        menu.addSubscriber(registerController);
        menu.onViewInit();
    }

}

