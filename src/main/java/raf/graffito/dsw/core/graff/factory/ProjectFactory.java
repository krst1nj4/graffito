package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class ProjectFactory implements GraffNodeStore{

    @Override
    public GraffNode createGraffNode(GraffNodeComposite parent) {
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(),
                "Unesite ime projekta:",
                "Novi projekat",
                JOptionPane.QUESTION_MESSAGE);

        if(name != null && !name.trim().isEmpty()){
            String author = JOptionPane.showInputDialog(MainFrame.getInstance(),
                    "Unesite ime autora:",
                    "Autor",
                    JOptionPane.QUESTION_MESSAGE);
            return new Project(name.trim(), parent, author != null ? author.trim() : "");
        }
        return null;
    }
}
