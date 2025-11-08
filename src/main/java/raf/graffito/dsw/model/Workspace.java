package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.repozitorijum.composite.GraffNode;
import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;

import javax.swing.tree.TreeNode;

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