package raf.graffito.dsw.core.graff.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
@Getter
@Setter
public class ImageElement extends SlideElement{

    private BufferedImage image;

    public ImageElement(GraffNode parent, String name, int x, int y, int width, int height, BufferedImage image) {
        super(parent, name, x, y, width, height);
        this.image = image;
    }

    public ImageElement(){
        super();
    }

    @Override
    public void paint(Graphics2D g) {
        if(image == null) return;

        AffineTransform oldTransform = g.getTransform();

        g.rotate(rotation, x + width / 2.0, y + height / 2.0);
        g.drawImage(image, x, y , width, height, null);
        g.setTransform(oldTransform);
    }

    @Override
    public ImageElement clone() {
        return (ImageElement) super.clone();
    }
}
