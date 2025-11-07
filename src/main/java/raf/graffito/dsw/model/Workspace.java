package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.repozitorijum.composite.GraffNode;
import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;

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
            if(!this.getChilds().contains(projekat)) {
                this.getChilds().add(projekat);
            }
        }
    }

    @Override
    public void removeChild(GraffNode cvor) {
        if(cvor != null && cvor instanceof Project) {
            Project projekat = (Project)cvor;
            if(this.getChilds().contains(projekat)) {
                this.getChilds().remove(projekat);
            }
        }
    }
}
//            public void removeChild(RuNode child) {
//        if (child != null && child instanceof Project) {
//            Project project = (Project) child;
//            if (this.getChildren().contains(project)) {
//                this.getChildren().remove(project);
//            }
//        }
