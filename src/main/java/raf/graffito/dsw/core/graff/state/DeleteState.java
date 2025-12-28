package raf.graffito.dsw.core.graff.state;


import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.strategy.ObrisiStrateigja;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State {
    private final SelectState selectState;

    public DeleteState(SelectState selectState) {
        this.selectState = selectState;
    }

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        izbrisiSelektovane(view);
    }

    public void izbrisiSelektovane(SlideView view){
        List<SlideElement> zaBrisanje = selectState.getSelected();

        if(zaBrisanje.isEmpty()){
            System.out.println("DeleteState: Nista nije selektovano za brisanje");
            return;
        }

        List<SlideElement> kopijaZaBrisanje = new ArrayList<>(zaBrisanje);


        ObrisiStrateigja komanda = new ObrisiStrateigja(view.getSlide(), kopijaZaBrisanje);
        view.getSlide().getKomandaManager().dodajKomandu(komanda);
//        for(SlideElement el : kopijaZaBrisanje){
//            view.getSlide().removeChild(el);
//        }

//        zaBrisanje.clear();

        view.getSlide().notifySubscribers(view.getSlide());

        System.out.println("DeleteState: Elementi uspesno obrisani.");
    }
}
