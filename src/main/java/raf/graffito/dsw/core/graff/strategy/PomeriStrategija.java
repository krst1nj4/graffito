package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.core.graff.model.SlideElement;

import java.util.List;

public class PomeriStrategija implements RadnjaStrategy{

    private List<SlideElement> elements;
    private int deltaX;
    private int deltaY;

    public PomeriStrategija(List<SlideElement> elements,  int deltaX, int deltaY) {
        this.elements = elements;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void izvrsi() {
        for (SlideElement element : elements) {
            element.setX(element.getX() + deltaX);
            element.setY(element.getY() + deltaY);
        }
    }

    @Override
    public void ponisti() {
        for (SlideElement element : elements) {
            element.setX(element.getX() - deltaX);
            element.setY(element.getY() - deltaY);
        }
    }
}
