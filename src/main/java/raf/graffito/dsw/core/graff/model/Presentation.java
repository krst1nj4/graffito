package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;

public class Presentation extends GraffNodeComposite {

    public Presentation(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(GraffNode cvor) {
        super.getChilds().add(cvor);
    }

    @Override
    public void removeChild(GraffNode cvor) {
        super.getChilds().remove(cvor);
    }
}
