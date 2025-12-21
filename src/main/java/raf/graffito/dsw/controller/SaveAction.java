package raf.graffito.dsw.controller;

import com.sun.tools.javac.Main;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAction extends AbstractGraffAction {

    public SaveAction() {
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save Project to a current file path");
        /// putValue(SMALL_ICON, loadIcon(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        DefaultMutableTreeNode selectedNode = MainFrame.getInstance().getGraffTree().getSelectedNode();

        if(!(selectedNode instanceof GraffTreeItem)){
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "You have to select a project!");
            return;
        }

        GraffTreeItem item = (GraffTreeItem) selectedNode;
        if(!(item.getGraffNode() instanceof Project)){
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.GRESKA, "Selected node is not a project! Saving can be done only for projects!");
            return;
        }
        Project pr = (Project) item.getGraffNode();
        if(pr == null){
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "There are no active projects to save.");
            return;
        }

        if(!pr.isChanged()){
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "NO changes were made");
            return;
        }

        if(pr.getFilePath() == null){
            /// ako nije snimljen pre, pozivamo Save As akciju
            MainFrame.getInstance().getActionManager().getSaveAsAction();
        } else {
            ApplicationFramework.getInstance().getSerializer().saveProject(pr, new File(pr.getFilePath()));
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Project saved successfully.");
        }
    }
}
