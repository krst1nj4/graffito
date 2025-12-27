package raf.graffito.dsw.controller;

import com.sun.tools.javac.Main;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.tree.model.GraffTree;
import raf.graffito.dsw.tree.model.GraffTreeImplements;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadProjectAction extends AbstractGraffAction {

    public LoadProjectAction() {
        putValue(NAME, "Load");
        putValue(SHORT_DESCRIPTION, "Load Project from a file");
        putValue(SMALL_ICON, loadIcon("/images/loadicon.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Select Project to Load");

        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();

            Project loadedProject = ApplicationFramework.getInstance().getSerializer().loadProject(file);

            if(loadedProject != null) {
//                ApplicationFramework.getInstance().getGraffRepository().getWorkspace().addChild(loadedProject);
//                GraffTreeImplements tree = MainFrame.getInstance().getGraffTree();
//                DefaultMutableTreeNode root = (DefaultMutableTreeNode) ((DefaultTreeModel) tree.getModel)
                ApplicationFramework.getInstance().getGraffRepository().getWorkspace().addChild(loadedProject);
                MainFrame.getInstance().getGraffTree().loadProject(loadedProject);
                MainFrame.getInstance().getGraffTree().refreshTree();

                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Project " + loadedProject.getName() + " has been loaded");
            } else {
                ApplicationFramework.getInstance().getDialogMsgGenerator()
                        .generateMessage(MessageType.GRESKA, "Error occured while loading selected project");
            }
        }
    }
}
