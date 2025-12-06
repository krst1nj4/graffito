package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstractGraffAction {

    public DeleteNodeAction() {
        putValue(NAME, "Delete Node");
        putValue(SMALL_ICON, loadIcon("/images/deleteicon.png"));
        putValue(SHORT_DESCRIPTION, "Brisanje cvora");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();
        DefaultMutableTreeNode selektovani = mf.getGraffTree().getSelectedNode();

        if (selektovani == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.GRESKA, "Izaberite cvor za brisanje");
            return;
        }

        if(((GraffTreeItem) selektovani).getGraffNode() instanceof Project) {
           /// ApplicationFramework
        }

        mf.getGraffTree().deleteChild(selektovani);
    }
}
