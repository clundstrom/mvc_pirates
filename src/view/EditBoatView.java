package view;

import controller.RegisterController;
import model.Boat;
import model.IViewObserver;

public class EditBoatView extends MenuView {
    public EditBoatView(RegisterController controller) {
        super(controller);
    }






    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param boat
     */
    protected void notifyBoatSubscribers(Boat boat) {
        for (IViewObserver sub : mSubscribers) {
            sub.onBoatCreated(boat);
        }
    }



}
