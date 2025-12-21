package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsAction extends AbstractGraffAction {

    public SaveAsAction() {
        putValue(NAME, "Save As");
        putValue(SHORT_DESCRIPTION, "Save Project to a new file path");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project project = MainFrame.getInstance().getProjectView().getProject();
        if (project == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "There is no active project to save.");
            return;
        }

        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Choose path to save project");

        if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();

            /// proveravamo da li se ime fajla zavrsava sa .json, ako ne dodajemo ga
            if(!file.getName().toLowerCase().endsWith(".json")) {
                file = new File(file.getAbsolutePath() + ".json");
            }

            ApplicationFramework.getInstance().getSerializer().saveProject(project, file);
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Project saved successfully on a new path: " + file.getAbsolutePath());
        }
    }
}
