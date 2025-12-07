package raf.graffito.dsw.core.graff.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;

@Getter
@Setter

public class Workspace extends GraffNodeComposite {


    public Workspace(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(GraffNode cvor) {
        if(cvor != null && cvor instanceof Project) {
            Project projekat = (Project)cvor;
            if(!this.getChildren().contains(projekat)) {
                this.getChildren().add(projekat);
            }
        }
    }

    @Override
    public void removeChild(GraffNode cvor) {
        if(cvor != null && cvor instanceof Project) {
            Project projekat = (Project)cvor;
            if(this.getChildren().contains(projekat)) {
                this.getChildren().remove(projekat);
            }
        }
    }
}