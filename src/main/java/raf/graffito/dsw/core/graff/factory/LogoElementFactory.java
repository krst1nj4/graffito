package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.LogoElement;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.space.SpaceValidator;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class LogoElementFactory implements SlideElementFactory {

    @Override
    public SlideElement createSlideElement(Slide slide) {

        boolean imaMesta = SpaceValidator.getInstance().checkSpace(slide, 200, 200, 120, 120);

        if (!imaMesta) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Nema dovoljno mesta za logo!",
                    "Greska", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new LogoElement(slide, "LogoElement", 200, 200 ,120, 120);
    }
}
