package raf.graffito.dsw.core.graff.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;

@Getter
@Setter
public class SlideElement extends GraffNode {
    protected int x,y, width, height;
    protected double rotation = 0;

    public SlideElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}
