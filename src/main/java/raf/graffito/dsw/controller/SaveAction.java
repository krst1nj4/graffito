package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAction extends AbstractGraffAction {

    public SaveAction() {
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save Project to a current file path");
        /// putValue(SMALL_ICON, loadIcon(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project pr = MainFrame.getInstance().getProjectView().getProject();
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
        } else {
            ApplicationFramework.getInstance().getSerializer().saveProject(pr, new File(pr.getFilePath()));
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Project saved successfully.");
        }
    }
}
