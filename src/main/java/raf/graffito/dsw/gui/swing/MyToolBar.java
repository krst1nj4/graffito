package raf.graffito.dsw.gui.swing;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAct());
        add(MainFrame.getInstance().getActionManager().getNewProjectAct());


    }
}
