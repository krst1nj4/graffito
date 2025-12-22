package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;

public class ResizeState implements State {

    private final SelectState selectState;
    private double prosloX, prosloY;

    public ResizeState(SelectState selectState) {
        this.selectState = selectState;
    }

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        prosloX = x;
        prosloY = y;
    }

    @Override
    public void misPovucen(double x, double y, SlideView view) {
        if(selectState.getSelected().isEmpty()) return;

        double razlikaX = x - prosloX;
        double razlikaY = y - prosloY;

        for(SlideElement el : selectState.getSelected()) {
            el.setWidth((int) Math.max(10, el.getWidth() + razlikaX));
            el.setHeight((int) Math.max(10, el.getHeight() + razlikaY));
        }
        prosloX = x;
        prosloY = y;
        view.getSlide().notifySubscribers(view.getSlide());
    }
}
