package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.LogoElement;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

public class LogoElementFactory implements SlideElementFactory {

    @Override
    public SlideElement createSlideElement(Slide slide) {
        return new LogoElement(slide, "LogoElement", 200, 200 ,120, 120);
    }
}
