package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.awt.event.MouseEvent;

public class ResizeState implements State {

    private Slide slide;
    private SelectState  selectState;

    public ResizeState(StateManager m, SelectState selectState) {
        this.selectState = selectState;
    }

    public void setSlide(Slide slide) {this.slide = slide;}

    @Override
    public void mouseDragged(MouseEvent e) {
        for(SlideElement el : selectState.selected){
            el.setWidth(el.getWidth() +1);
            el.setHeight(el.getHeight() +1);
        }
        slide.notifySubscribers(slide);
    }
}
