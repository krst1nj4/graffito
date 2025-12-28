package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.core.graff.model.SlideElement;

import java.util.List;

public class RotirajStrategija implements RadnjaStrategy{

    private List<SlideElement> elements;
    private double angleDelta;

    public RotirajStrategija(List<SlideElement> elements, double angleDelta) {
        this.elements = elements;
        this.angleDelta = angleDelta;
    }

    @Override
    public void izvrsi() {
        for (SlideElement element : elements) {
            element.setRotation(element.getRotation() + angleDelta);
        }
    }

    @Override
    public void ponisti() {
        for (SlideElement element : elements) {
            element.setRotation(element.getRotation() - angleDelta);
        }
    }
}
