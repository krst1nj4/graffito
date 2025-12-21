package raf.graffito.dsw.core.graff.state;

import lombok.Getter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class SelectState implements State{

    @Getter private List<SlideElement> selected = new ArrayList<>();
    @Getter private Rectangle2D lasoPravougaonik = null;
    private double startX, startY;
    public SelectState() {}

    @Override
    public void misKliknut(double x, double y, SlideView view) {
        startX = x;
        startY = y;
        SlideElement pogodjen = null;
        for(GraffNode node : view.getSlide().getChildren()){
            if(node instanceof SlideElement el){
                if(x >= el.getX() && x <= el.getX() + el.getWidth() && y >= el.getY() && y <= el.getY() + el.getHeight()){
                    pogodjen = el;
                }
            }
        }
        if(pogodjen != null){
            if(!selected.contains(pogodjen)){
                selected.clear();
                selected.add(pogodjen);
            }
            lasoPravougaonik = null;
        }else {
            selected.clear();
            lasoPravougaonik = new Rectangle2D.Double(x, y, 0, 0);
        }
        view.getSlide().notifySubscribers(view.getSlide());
    }

    @Override
    public void misPovucen(double x, double y, SlideView view) {
        if(lasoPravougaonik != null){
            double nx = Math.min(x, startX);
            double ny = Math.min(y, startY);
            double nw = Math.abs(x - startX);
            double ne = Math.abs(y - startY);
            lasoPravougaonik.setRect(nx, ny, nw, ne);
            view.repaint();
        }
    }

    @Override
    public void misOtpusten(double x, double y, SlideView view) {
        if(lasoPravougaonik != null){
            for(GraffNode node : view.getSlide().getChildren()){
                if(node instanceof SlideElement el){
                    if(lasoPravougaonik.intersects(el.getX(), el.getY(), el.getWidth(), el.getHeight())){
                        selected.add(el);
                    }
                }
            }
            lasoPravougaonik = null;
        }
        view.getSlide().notifySubscribers(view.getSlide());
    }
}
