package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class SlideFactory implements GraffNodeStore{

    @Override
    public GraffNode createGraffNode(GraffNodeComposite parent) {
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(),
                "Unesite ime za slide:",
                "Novi slide",
                JOptionPane.QUESTION_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            Slide slide = new Slide(name, parent);
            slide.setName(name.trim());
            slide.setParent(parent);
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Kreirani slajd:" + name);

            return slide;
        }else{
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.GRESKA, "Morate uneti ime!");
            return null;
        }
    }
}
