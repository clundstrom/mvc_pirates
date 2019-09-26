import com.google.gson.Gson;
import controller.MemberController;
import view.Menu;

public class Program {


    public static void main(String[] args) {
        MemberController mc = new MemberController();
        Menu menu = new Menu();
        menu.addSubscriber(mc);
        Gson json = new Gson();
        menu.presentation();

    }

}

