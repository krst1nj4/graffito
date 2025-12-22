package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.model.TextElement;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class TextElementFactory implements SlideElementFactory {



    @Override
    public SlideElement createSlideElement(Slide slide) {

        String text = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite tekst:", "Tekst", JOptionPane.QUESTION_MESSAGE);

        if(text == null || text.trim().isEmpty()) return null;

        return new TextElement(slide, "text", 150, 150, 200, 40, text);
    }
}
