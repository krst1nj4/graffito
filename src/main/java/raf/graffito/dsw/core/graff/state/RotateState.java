package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.awt.event.MouseEvent;

public class RotateState implements State{
    private Slide slide;
    private SelectState selectState;
    private boolean clockwise;

    public RotateState(StateManager m , SelectState selectState) {
        this.selectState = selectState;
    }

    public void setSlide(Slide slide){this.slide = slide;}

    @Override
    public void mousePressed(MouseEvent e) {
        double angle = Math.toRadians(clockwise ? 90 : -90);

        for(SlideElement el : selectState.selected){
            el.setRotation(el.getRotation() + angle);
        }
        slide.notifySubscribers(slide);
    }
}
