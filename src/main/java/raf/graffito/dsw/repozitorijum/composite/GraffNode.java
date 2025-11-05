package raf.graffito.dsw.repozitorijum.composite;

public abstract class GraffNode {
    private GraffNode parent;
    private String name;

    public GraffNode(GraffNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public GraffNode findByName(String name){
        if(this.name.equals(name)) return this;
        return null;
    }


}
