package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.util.List;

public class ObrisiStrateigja implements RadnjaStrategy {

    private Slide slide;
    private List<SlideElement> elementsToDelete;

    public ObrisiStrateigja(Slide slide, List<SlideElement> elementsToDelete) {
        this.slide = slide;
        this.elementsToDelete = elementsToDelete;
    }


    @Override
    public void izvrsi() {
        for (SlideElement element : elementsToDelete) {
            slide.removeChild(element);
        }
    }

    @Override
    public void ponisti() {
        for (SlideElement element : elementsToDelete) {
            slide.addChild(element);
        }
    }
}
