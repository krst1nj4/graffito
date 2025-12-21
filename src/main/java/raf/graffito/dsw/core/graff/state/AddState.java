package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.factory.SlideElementFactory;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.awt.event.MouseEvent;

public class AddState implements State {

    private Slide slide;
    private SlideElementFactory slideElementFactory;

    public AddState(Slide slide, SlideElementFactory slideElementFactory) {
        this.slide = slide;
        this.slideElementFactory = slideElementFactory;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        SlideElement element = slideElementFactory.createSlideElement(slide);
        if(element == null) return;

        element.setX(e.getX());
        element.setY(e.getY());

        slide.addChild(element);

        slide.notifySubscribers(slide);
    }
}
