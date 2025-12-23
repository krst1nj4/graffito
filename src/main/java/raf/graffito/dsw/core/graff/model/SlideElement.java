package raf.graffito.dsw.core.graff.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeLeaf;

import java.awt.*;

@Getter
@Setter
public abstract class SlideElement extends GraffNodeLeaf implements Cloneable {
    protected int x,y, width, height;
    protected double rotation = 0;

    public SlideElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public SlideElement() {
        super();
    }

    @Override
    public SlideElement clone() {
        try {
            return (SlideElement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void paint(Graphics2D g);
}
