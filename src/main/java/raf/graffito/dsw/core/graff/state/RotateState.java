package raf.graffito.dsw.core.graff.state;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.strategy.RotirajStrategija;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RotateState implements State{
    private final SelectState selectState;
    private static final double UGAO_90 = Math.toRadians(90);

    @Setter private boolean uSmeruKazaljke = true;

    public RotateState(SelectState selectState) {
        this.selectState = selectState;
    }

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        izvrsiRotaciju(view);
    }

    public void izvrsiRotaciju(SlideView view) {
        if(selectState.getSelected().isEmpty()) return;

        double ugao = uSmeruKazaljke ? UGAO_90 : -UGAO_90;

        List<SlideElement> elementi = new ArrayList<>(selectState.getSelected());
        RotirajStrategija komanda = new RotirajStrategija(elementi, ugao);

        view.getSlide().getKomandaManager().dodajKomandu(komanda);

        view.getSlide().notifySubscribers(view.getSlide());
    }
}
