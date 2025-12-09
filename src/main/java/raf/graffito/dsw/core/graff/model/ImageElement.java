package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageElement extends SlideElement{

    private BufferedImage image;

    public ImageElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name, x, y, width, height);
        this.image = image;
    }

    public void paint(Graphics2D g) {
        g.rotate(rotation, x + width / 2, y + height / 2);
        g.drawImage(image, x, y , width, height, null);
        g.rotate(-rotation, x + width / 2, y + height / 2);
    }
}
