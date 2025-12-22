package raf.graffito.dsw.core.graff.state;

import lombok.Setter;
import raf.graffito.dsw.core.graff.factory.SlideElementFactory;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;

public class AddState implements State {
    @Setter private SlideElementFactory factory;

    public AddState() {}

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        if(factory == null) return;
        SlideElement novi = factory.createSlideElement(view.getSlide());
        if(novi != null){
            novi.setX((int) x);
            novi.setY((int) y);
            view.getSlide().addChild(novi);
        }
    }
}
