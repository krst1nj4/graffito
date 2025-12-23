package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeLeaf;
import raf.graffito.dsw.core.graff.view.LogoPainter;

import java.awt.*;

public class LogoElement extends SlideElement {

    public LogoElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name ,x, y, width, height);
    }

    public LogoElement() {
        super();
    }

    @Override
    public void paint(Graphics2D g) {
        new LogoPainter(this).paint(g);
    }

    @Override
    public LogoElement clone(){return (LogoElement) super.clone();}
}
