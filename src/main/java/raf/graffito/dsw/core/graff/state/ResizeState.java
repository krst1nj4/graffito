package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.strategy.PromeniVelicinuStrategija;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ResizeState implements State {

    private final SelectState selectState;
    private double prosloX, prosloY;

    private int totalDeltaW = 0;
    private int totalDeltaH = 0;

    public ResizeState(SelectState selectState) {
        this.selectState = selectState;
    }

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        prosloX = x;
        prosloY = y;
        totalDeltaW = 0;
        totalDeltaH = 0;
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

        totalDeltaW += razlikaX;
        totalDeltaH += razlikaY;

        prosloX = x;
        prosloY = y;
        view.getSlide().notifySubscribers(view.getSlide());
    }

    @Override
    public void misOtpusten(double x, double y, SlideView view) {
        if(selectState.getSelected().isEmpty()) return;
        if(totalDeltaW == 0 && totalDeltaH == 0) return;

        for(SlideElement el : selectState.getSelected()) {
            el.setWidth((int) Math.max(10, el.getWidth() - totalDeltaW));
            el.setHeight((int) Math.max(10, el.getHeight() - totalDeltaH));
        }

        List<SlideElement> elementi = new ArrayList<>(selectState.getSelected());
        PromeniVelicinuStrategija komanda = new PromeniVelicinuStrategija(elementi, totalDeltaW, totalDeltaH);

        view.getSlide().getKomandaManager().dodajKomandu(komanda);

        totalDeltaW = 0;
        totalDeltaH = 0;
    }
}
