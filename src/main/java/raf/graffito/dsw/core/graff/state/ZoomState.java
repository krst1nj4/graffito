package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseWheelEvent;

public class ZoomState implements State{
    private final SlideView slideView;

    private static final double zoomStep = 0.1;
    private static final double zoomMin = 0.3;
    private static final double zoomMax = 3.0;


    public ZoomState(SlideView slideView) {
        this.slideView = slideView;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(slideView == null) return;

        double scale = slideView.getScale();

        if(e.getPreciseWheelRotation() < 0){
            scale += zoomStep;
            if(scale > zoomMax) scale = zoomMax;

            slideView.setScale(scale);
        }else{
            scale -= zoomStep;
            if(scale < zoomMin) scale = zoomMin;

            slideView.setScale(scale);
        }


    }
}
