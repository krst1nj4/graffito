package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;

public interface GraffNodeStore {
    GraffNode createGraffNode(GraffNodeComposite parent);
}
