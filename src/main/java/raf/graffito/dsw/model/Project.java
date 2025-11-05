package raf.graffito.dsw.model;

import raf.graffito.dsw.repozitorijum.composite.GraffNode;
import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;

public class Project extends GraffNodeComposite {

    private String autor;
    private int brSlajdova;

    public Project(GraffNode parent, String name) {
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
