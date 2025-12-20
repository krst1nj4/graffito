package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.dialogs.EditDialog;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;

public class EditAction extends AbstractGraffAction{

    public EditAction() {
        putValue(SMALL_ICON, loadIcon("/images/editicon.png"));
        putValue(NAME, "Menjanje cvora");
        putValue(SHORT_DESCRIPTION, "Promena imena i autora projekta");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTree tree = (JTree) MainFrame.getInstance().getGraffTree();
        MainFrame mf = MainFrame.getInstance();

        TreePath path = tree.getSelectionPath();
        if (path == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator()
                    .generateMessage(MessageType.UPOZORENJE, "Niste selektovali nijedan čvor u stablu!");
            return;
        }

        Object selected = path.getLastPathComponent();
        if (!(selected instanceof GraffTreeItem)) {
            return;
        }
        GraffTreeItem item = (GraffTreeItem) selected;

        if (!(item.getGraffNode() instanceof Project)) {
            ApplicationFramework.getInstance().getDialogMsgGenerator()
                    .generateMessage(MessageType.GRESKA, "Selektovani čvor nije Projekat!");
            return;
        }

        Project project = (Project) item.getGraffNode();
        EditDialog ed = new EditDialog(project);


        if(ed.showDialog(mf)) {
            String newName = ed.getProjectName();
            String newAuthor = ed.getAuthor();

            if(!newName.isEmpty()) {
                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Preimenovano ime.");
                project.setName(newName);
                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Preimenovan autor.");
                project.setAuthor(newAuthor);
                mf.getGraffTree().refreshTree();
            } else {
                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "Ime projekta ne moze biti prazno!");
            }
        }

    }
}
