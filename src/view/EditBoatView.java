package view;

import controller.RegisterController;
import model.IViewObserver;

public class EditBoatView extends MenuView {
    public EditBoatView(RegisterController controller) {
        super(controller);
    }






    /**
     * Notifies any subscribers with provided information from the view.
     *
     * @param boatInformation
     */
    protected void notifyBoatSubscribers(String[] boatInformation) {
        for (IViewObserver sub : mSubscribers) {
            sub.onBoatUpdated(boatInformation);
        }
    }



}
