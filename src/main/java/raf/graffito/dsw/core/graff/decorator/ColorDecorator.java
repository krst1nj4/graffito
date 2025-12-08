package raf.graffito.dsw.core.graff.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;

import java.awt.*;


@Getter
@Setter
public class ColorDecorator extends NodeDecorator {
    private Color color;

    public ColorDecorator(GraffNode decoratedNode) {
        super(decoratedNode);
    }

    public ColorDecorator(GraffNode decoratedNode, Color color) {
        super(decoratedNode);
        this.color = color;
    }

    public int getRGB(){
        return color != null ? color.getRGB() : 0;
    }

    public void setRGB(int rgb){
        color = new Color(rgb);
    }
}
