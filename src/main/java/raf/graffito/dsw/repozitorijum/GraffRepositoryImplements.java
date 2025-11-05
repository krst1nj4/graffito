package raf.graffito.dsw.repozitorijum;

import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.repozitorijum.composite.GraffNode;
import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;
import raf.graffito.dsw.model.Workspace;

public class GraffRepositoryImplements implements GraffRepository {
    private Workspace workspace;

    public GraffRepositoryImplements() {
        workspace = new Workspace(null, "My Workspace");
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void addChild(GraffNodeComposite parent, GraffNode child) {

    }
}
