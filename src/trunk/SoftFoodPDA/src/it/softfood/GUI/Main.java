package it.softfood.GUI;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Main extends SingleFrameApplication {

    @Override protected void startup() {
        show(new MainView(this));
    }

    @Override protected void configureWindow(java.awt.Window root) {
    }

    public static Main getApplication() {
        return Application.getInstance(Main.class);
    }

    public static void main(String[] args) {
        launch(Main.class, args);
    }
    
}
