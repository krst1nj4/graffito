package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.util.List;

public class PasteStrategija implements RadnjaStrategy{

    private Slide slide;
    private List<SlideElement> elementsToPaste;

    public PasteStrategija(Slide slide, List<SlideElement> elementsToPaste) {
        this.slide = slide;
        this.elementsToPaste = elementsToPaste;
    }

    @Override
    public void izvrsi() {
        for (SlideElement element : elementsToPaste) {
            if (!slide.getChildren().contains(element)) {
                slide.addChild(element);
            }
        }
    }

    @Override
    public void ponisti() {
        for (SlideElement element : elementsToPaste) {
            slide.removeChild(element);
        }
    }
}
