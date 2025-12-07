package raf.graffito.dsw.core.graff.composite;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class GraffNode {
    private GraffNode parent;
    private String name;

    public GraffNode findByName(String name){
        if(this.name.equals(name)) return this;
        return null;
    }


}
