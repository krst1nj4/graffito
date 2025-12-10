package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectState implements State{

    private Slide slide;
    public List<SlideElement> selected = new ArrayList<>();

    public SelectState(StateManager m) {}

    public void setSlide(Slide slide) {this.slide = slide;}

    @Override
    public void mousePressed(MouseEvent e) {
        selected.clear();
        for(GraffNode node : slide.getChildren() ){
            if(node instanceof SlideElement el){
                if(e.getX() >= el.getX() && e.getX() <= el.getX() + el.getWidth() && e.getY() >= el.getY() && e.getY() <= el.getY() + el.getHeight() ){
                    selected.add(el);
                }
            }
        }
        slide.notifySubscribers(slide);
    }
}
