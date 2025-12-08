package raf.graffito.dsw.core;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.decorator.ColorDecorator;
import raf.graffito.dsw.core.graff.factory.GraffNodeStore;
import raf.graffito.dsw.core.graff.model.Workspace;

import java.awt.*;
import java.util.List;
import java.util.Set;

public interface GraffRepository {
    Workspace getWorkspace();
    List<ColorDecorator> getProjectDecorators();
    Set<Color> getUsedColors();

    GraffNodeStore createFactory(String type);
    void deleteColor(GraffNode graffNode);
}
