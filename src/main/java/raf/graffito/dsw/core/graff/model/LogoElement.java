package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeLeaf;

import java.awt.*;

public class LogoElement extends SlideElement {

public LogoElement(GraffNode parent, String name, int x, int y, int width, int height) {
    super(parent, name ,x, y, width, height);
}

    @Override
    public void paint(Graphics2D g) {

    }

    @Override
    public LogoElement clone(){return (LogoElement) super.clone();}
}
