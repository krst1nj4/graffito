package raf.graffito.dsw.core.graff;

import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.factory.GraffNodeStore;
import raf.graffito.dsw.core.graff.factory.PresFactory;
import raf.graffito.dsw.core.graff.factory.ProjectFactory;
import raf.graffito.dsw.core.graff.factory.SlideFactory;
import raf.graffito.dsw.core.graff.model.Workspace;

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

    @Override
    public GraffNodeStore createFactory(String type) {
        switch(type) {
            case "project":
                return new ProjectFactory();
            case "presentation" :
                return new PresFactory();
            case "slide":
                return new SlideFactory();
        }
        return null;
    }
}
