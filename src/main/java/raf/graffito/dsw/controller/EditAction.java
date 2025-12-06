package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.EditDialog;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;

public class EditAction extends AbstractGraffAction{

    public EditAction() {
        putValue(SMALL_ICON, loadIcon("/images/editicon.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Promena imena i autora projekta");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();
        DefaultMutableTreeNode selektovani = mf.getGraffTree().getSelectedNode();

        if(selektovani == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.GRESKA, "Morate izabrati projekat!");
            return;
        }

        if(!(selektovani instanceof GraffTreeItem)) {
            return;
        }

        GraffTreeItem item = (GraffTreeItem) selektovani;

        if(!(item.getParent() instanceof Project)) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.GRESKA, "Izabrani cvor nije projekat!");
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
                project.setAutor(newAuthor);
                mf.getGraffTree().refreshTree();
            } else {
                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "Ime projekta ne moze biti prazno!");
            }
        }


    }
}
