package raf.graffito.dsw.gui.swing;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);


        /// stablo:
        add (MainFrame.getInstance().getActionManager().getNewNodeAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getEditAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAct());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());

        /// osnovne akcije:
        add(MainFrame.getInstance().getActionManager().getAboutUsAct());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getExitAct());

    }
}
