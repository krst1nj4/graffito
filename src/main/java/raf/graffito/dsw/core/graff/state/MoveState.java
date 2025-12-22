package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;

public class MoveState implements State {
    private final SelectState selectState;
    private double lastX, lastY;

    public MoveState(SelectState selectState) {
        this.selectState = selectState;
    }

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        lastX = x;
        lastY = y;
    }

    @Override
    public void misPovucen(double x, double y, SlideView view) {
        double dx  = x - lastX;
        double dy = y - lastY;
        for(SlideElement el : selectState.getSelected()) {
            el.setX((int) (el.getX() + dx));
            el.setY((int) (el.getY() + dy));
        }
        lastX = x;
        lastY = y;
        view.getSlide().notifySubscribers(view.getSlide());
    }
}
