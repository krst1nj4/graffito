package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TextElement extends SlideElement {

    private String text;

    public TextElement(GraffNode parent, String name, int x, int y, int width, int height, String text) {
        super(parent, name, x, y, width, height);
        this.text = text;
    }

    @Override
    public void paint(Graphics2D g) {
        AffineTransform old =  g.getTransform();

        g.setColor(Color.BLACK);
        g.rotate(rotation, x + width / 2.0, y + height / 2.0);
        g.drawString(text, x, y +  height);

        g.setTransform(old);
    }

    @Override
    public TextElement clone() {
        return (TextElement) super.clone();
    }
}
