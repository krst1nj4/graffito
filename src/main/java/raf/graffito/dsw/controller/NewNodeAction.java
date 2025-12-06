package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractGraffAction{
    public NewNodeAction() {
        putValue(NAME, "New Node");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plusicon2.png"));
        putValue(SHORT_DESCRIPTION, "Create a new node");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();
        DefaultMutableTreeNode selektovani = mf.getGraffTree().getSelectedNode();

        if (selektovani == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "Morate selektovati cvor!");
            return;
        }

        mf.getGraffTree().createChild(selektovani);
    }
}
