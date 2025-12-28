package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.strategy.PomeriStrategija;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State {
    private final SelectState selectState;
    private double initialClickX, initialClickY; // Pozicija gde je kliknuto na pocetku
    private boolean dragging = false;
    private double lastX, lastY;

    public MoveState(SelectState selectState) {
        this.selectState = selectState;
    }

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        lastX = x;
        lastY = y;
        initialClickX = x;
        initialClickY = y;
        dragging = true;
    }

    @Override
    public void misPovucen(double x, double y, SlideView view) {
        if(!dragging || selectState.getSelected().isEmpty()) return;

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

    @Override
    public void misOtpusten(double x, double y, SlideView view) {
        if (!dragging) return;

        int totalDx = (int) (x - initialClickX);
        int totalDy = (int) (y - initialClickY);

        if (totalDx == 0 && totalDy == 0) return;

        for(SlideElement el : selectState.getSelected()){
            el.setX(el.getX() - totalDx);
            el.setY(el.getY() - totalDy);
        }

        List<SlideElement> elementi = new ArrayList<>(selectState.getSelected());
        PomeriStrategija komanda = new PomeriStrategija(elementi, totalDx, totalDy);

        view.getSlide().getKomandaManager().dodajKomandu(komanda);
    }
}
