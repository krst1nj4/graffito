package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class PresFactory implements GraffNodeStore{

    @Override
    public GraffNode createGraffNode(GraffNodeComposite parent) {
        String name = JOptionPane.showInputDialog((MainFrame.getInstance()),
                "Unesite ime prezentacije:",
                "Nova prezentacija",
                JOptionPane.QUESTION_MESSAGE);

        if(name != null && !name.trim().isEmpty()){
            return new Presentation(name.trim(), parent);
        }
        return null;
    }
}
