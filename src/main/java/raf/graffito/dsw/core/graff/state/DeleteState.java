package raf.graffito.dsw.core.graff.state;


import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.awt.event.MouseEvent;

public class DeleteState implements State {

    private Slide slide;
    private SelectState selectState;

    public DeleteState(Slide slide, SelectState selectState) {
        this.slide = slide;
        this.selectState = selectState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(SlideElement el : selectState.selected){
            slide.removeChild(el);
        }

        selectState.selected.clear();

        slide.notifySubscribers(slide);
    }
}
