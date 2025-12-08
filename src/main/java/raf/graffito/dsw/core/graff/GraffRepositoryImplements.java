package raf.graffito.dsw.core.graff;

import lombok.Getter;
import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.decorator.ColorDecorator;
import raf.graffito.dsw.core.graff.factory.GraffNodeStore;
import raf.graffito.dsw.core.graff.factory.PresFactory;
import raf.graffito.dsw.core.graff.factory.ProjectFactory;
import raf.graffito.dsw.core.graff.factory.SlideFactory;
import raf.graffito.dsw.core.graff.model.Workspace;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraffRepositoryImplements implements GraffRepository {
    private Workspace workspace;
    @Getter
    private List<ColorDecorator> projectDecorators = new ArrayList<ColorDecorator>();
    @Getter
    private Set<Color> usedColors = new HashSet<>();

    public GraffRepositoryImplements() {
        workspace = new Workspace(null,"My workspace");
    }


    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public GraffNodeStore createFactory(String type) {
        switch (type) {
            case "project":
                return new ProjectFactory();
            case "presentation":
                return new PresFactory();
            case "slide":
                return new SlideFactory();
        }
        return null;
    }

    @Override
    public void deleteColor(GraffNode graffNode) {
        ColorDecorator decorator = projectDecorators.stream()
                .filter(projectDecorator ->  projectDecorator.getDecoratedNode().equals(graffNode))
                .findFirst()
                .orElse(null);
        if (decorator != null) {
            usedColors.remove(decorator.getColor());
            projectDecorators.remove(decorator);
        }
    }
}
