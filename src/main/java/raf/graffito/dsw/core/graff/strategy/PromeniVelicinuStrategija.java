package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.core.graff.model.SlideElement;

import java.util.List;

public class PromeniVelicinuStrategija implements RadnjaStrategy{

    private List<SlideElement> elements;
    private int deltaW;
    private int deltaH;

    public PromeniVelicinuStrategija(List<SlideElement> elements, int deltaW, int deltaH) {
        this.elements = elements;
        this.deltaW = deltaW;
        this.deltaH = deltaH;
    }

    @Override
    public void izvrsi() {
        for (SlideElement el : elements) {
            el.setWidth((int) Math.max(10, el.getWidth() + deltaW));
            el.setHeight((int) Math.max(10, el.getHeight() + deltaH));
        }
    }

    @Override
    public void ponisti() {
        for (SlideElement el : elements) {
            el.setWidth((int) Math.max(10, el.getWidth() - deltaW));
            el.setHeight((int) Math.max(10, el.getHeight() - deltaH));
        }
    }
}
