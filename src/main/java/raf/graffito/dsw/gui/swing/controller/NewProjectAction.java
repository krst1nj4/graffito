package raf.graffito.dsw.gui.swing.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGraffAction{

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plusicon2.png"));
        putValue(NAME, "Novi projekat");
        putValue(SHORT_DESCRIPTION, "Novi projekat");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selektovan = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();
        MainFrame.getInstance().getGraffTree().addChild(selektovan);
    }
}
