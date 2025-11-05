package raf.graffito.dsw.core;

import raf.graffito.dsw.repozitorijum.composite.GraffNode;
import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;
import raf.graffito.dsw.model.Workspace;

public interface GraffRepository {
    Workspace getWorkspace();
    void addChild(GraffNodeComposite parent, GraffNode child);
}
