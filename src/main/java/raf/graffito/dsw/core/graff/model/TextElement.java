package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;

import java.awt.*;

public class TextElement extends SlideElement {

    private String text;

    public TextElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name, x, y, width, height);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.rotate(-rotation, x + width / 2, y + height / 2);
        g.drawString(text, x, y +  height);
        g.rotate(-rotation, x + width / 2, y + height / 2);
    }

}
