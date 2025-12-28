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
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());

        /// nemamo desni toolbar, pa proveravam ovde da li mi radi serijalizacija
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getLoadProjectAction());

        addSeparator();

        Action normalAction = MainFrame.getInstance().getActionManager().getNormalModeAction();
        Action smallAction = MainFrame.getInstance().getActionManager().getSmallModeAction();
        Action fullAction = MainFrame.getInstance().getActionManager().getFullModeAction();

        JRadioButton btnNormal = new JRadioButton(normalAction);
        JRadioButton btnSmall = new JRadioButton(smallAction);
        JRadioButton btnFull = new JRadioButton(fullAction);

        btnNormal.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(btnNormal);
        group.add(btnSmall);
        group.add(btnFull);

        add(new JLabel(" | Mode: "));
        add(btnNormal);
        add(btnSmall);
        add(btnFull);
    }
}
