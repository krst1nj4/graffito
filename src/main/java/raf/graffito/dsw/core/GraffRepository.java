package raf.graffito.dsw.core;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.factory.GraffNodeStore;
import raf.graffito.dsw.core.graff.model.Workspace;

public interface GraffRepository {
    Workspace getWorkspace();
    void addChild(GraffNodeComposite parent, GraffNode child);

    GraffNodeStore createFactory(String type);
}
