package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

public class DodajStrategija implements RadnjaStrategy{

    private Slide slide;
    private SlideElement element;

    public DodajStrategija(Slide slide, SlideElement element) {
        this.slide = slide;
        this.element = element;
    }


    @Override
    public void izvrsi() {
        if(!slide.getChildren().contains(element)){
            slide.addChild(element);
        }
    }

    @Override
    public void ponisti() {
        slide.removeChild(element);
    }
}
