package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.awt.event.MouseEvent;

public class MoveState implements State {
    private Slide slide;
    private SelectState  selectState;
    private int lastX, lastY;

    public MoveState(StateManager m ,SelectState selectState) {
        this.selectState = selectState;
    }

    public void setSlide(Slide s){this.slide = s;}

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx =  e.getX() - lastX;
        int dy =  e.getY() - lastY;

        for(SlideElement el : selectState.selected){
            el.setX(el.getX() + dx);
            el.setY(el.getY() + dy);
        }
        lastX = e.getX();
        lastY = e.getY();

        slide.notifySubscribers(slide);
    }
}
