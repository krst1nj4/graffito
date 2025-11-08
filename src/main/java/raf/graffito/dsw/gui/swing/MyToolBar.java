package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.gui.swing.controller.AboutUsAction;
import raf.graffito.dsw.gui.swing.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAct());
        add(MainFrame.getInstance().getActionManager().getAboutUsAct());
        add(MainFrame.getInstance().getActionManager().getNewProjectAct());


    }
}
