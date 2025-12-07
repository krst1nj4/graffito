package raf.graffito.dsw.core.graff.composite;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter

public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children;

    public GraffNodeComposite(GraffNode parent, String name) {
        super(parent, name);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(GraffNode cvor);

    public abstract void removeChild(GraffNode cvor);

    @Override
    public GraffNode findByName(String name) {
            GraffNode found = super.findByName(name);
            if(found != null) return found;

            for(GraffNode child : children){
                found = child.findByName(name);
                if(found != null) return found;
            }
        return null;
    }
}
