package raf.graffito.dsw.core.graff.composite;

public abstract class GraffNodeLeaf extends GraffNode {
    @Override
    public GraffNode findByName(String name) {
        if(this.getName().equals(name))
            return this;
        return null;
    }
}
