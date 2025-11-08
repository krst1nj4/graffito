package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Gui;

public class SwingGui implements Gui {
    private MainFrame instance;

    public SwingGui(){

    }


    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }
}
