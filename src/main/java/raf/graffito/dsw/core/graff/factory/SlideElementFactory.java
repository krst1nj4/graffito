package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

public interface SlideElementFactory {
    SlideElement createSlideElement(Slide slide);
}
