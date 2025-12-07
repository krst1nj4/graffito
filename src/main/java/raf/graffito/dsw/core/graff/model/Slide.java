package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeLeaf;

public class Slide extends GraffNodeLeaf {

    public Slide(String Name, GraffNode parentNode) {
        super();

        setName(Name);
        setParent(parentNode);
    }
    public Slide() {
        super();
    }
}
