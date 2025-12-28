package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.model.TextElement;
import raf.graffito.dsw.core.graff.space.SpaceValidator;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TextElementFactory implements SlideElementFactory {



    @Override
    public SlideElement createSlideElement(Slide slide) {

        String text = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite tekst:", "Tekst", JOptionPane.QUESTION_MESSAGE);

        if(text == null || text.trim().isEmpty()) return null;


        Font font = new Font("Arial", Font.PLAIN, 20);
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        int finalWidth = textWidth + 10;
        int finalHeight = textHeight + 10;

        boolean imaMesta = SpaceValidator.getInstance().checkSpace(slide, 150, 150, finalWidth, finalHeight);

        if (!imaMesta) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Nema dovoljno mesta za ovoliki tekst! (Popunjenost > 80%)",
                    "Greska", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new TextElement(slide, "text", 150, 150, finalWidth, finalHeight, text);
    }
}
