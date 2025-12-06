package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class SlideFactory implements GraffNodeStore{

    @Override
    public GraffNode createGraffNode(GraffNodeComposite parent) {
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(),
                "Unesite ime za slide:",
                "Novi slide",
                JOptionPane.QUESTION_MESSAGE);

        if(name != null && !name.trim().isEmpty()){
            return new Slide(parent, name.trim());
        }
        return null;
    }
}
