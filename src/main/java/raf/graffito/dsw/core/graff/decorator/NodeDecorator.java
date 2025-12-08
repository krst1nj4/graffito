package raf.graffito.dsw.core.graff.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import raf.graffito.dsw.core.graff.composite.GraffNode;

@Getter
@AllArgsConstructor
public abstract class NodeDecorator extends GraffNode {
    private GraffNode decoratedNode;

    @Override
    public GraffNode findByName(String name) {
        return decoratedNode.findByName(name);
    }

    @Override
    public String getName() {
        return decoratedNode.getName();
    }

    @Override
    public GraffNode getParent() {
        return decoratedNode.getParent();
    }
}
