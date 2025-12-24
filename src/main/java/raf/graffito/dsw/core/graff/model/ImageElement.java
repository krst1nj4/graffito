package raf.graffito.dsw.core.graff.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.proxy.ISlideImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
@Getter
@Setter
public class ImageElement extends SlideElement{

    private ISlideImage image;

    public ImageElement(GraffNode parent, String name, int x, int y, int width, int height, ISlideImage image) {
        super(parent, name, x, y, width, height);
        this.image = image;
    }

    @Override
    public void paint(Graphics2D g) {
        if(image == null) return;

        AffineTransform oldTransform = g.getTransform();

        g.rotate(rotation, x + width / 2.0, y + height / 2.0);
        g.translate(x, y);
        image.paint(g, width, height);

        g.setTransform(oldTransform);
    }

    @Override
    public ImageElement clone() {
        return (ImageElement) super.clone();
    }
}
