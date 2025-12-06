package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Gui;

public class SwingGui implements Gui {
    public SwingGui(){

    }


    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }
}
