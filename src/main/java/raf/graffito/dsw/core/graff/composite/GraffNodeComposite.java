package raf.graffito.dsw.core.graff.composite;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter

public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> childs;

    public GraffNodeComposite(GraffNode parent, String name) {
        super(parent, name);
        this.childs = new ArrayList<>();
    }

    public abstract void addChild(GraffNode cvor);

    public abstract void removeChild(GraffNode cvor);

    @Override
    public GraffNode findByName(String name) {
            GraffNode found = super.findByName(name);
            if(found != null) return found;

            for(GraffNode child : childs){
                found = child.findByName(name);
                if(found != null) return found;
            }
        return null;
    }
}
