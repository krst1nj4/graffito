package raf.graffito.dsw.core.graff.state;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseWheelEvent;
@Getter
@Setter
public class ZoomState implements State{


    private static final double zoomStep = 0.1;
    private static final double zoomMin = 0.3;
    private static final double zoomMax = 3.0;


    public ZoomState() {}

    @Override
    public void misSkrolovan(double rotacija, SlideView view) {
        double trenutnaSkala = view.getScale();
        double novaSkala;

        if(rotacija < 0){
            novaSkala = trenutnaSkala + zoomStep;
        } else {
            novaSkala = trenutnaSkala - zoomStep;
        }

        if(novaSkala < zoomMin){
            novaSkala = zoomMin;
        }else if(novaSkala > zoomMax){
            novaSkala = zoomMax;
        }

        view.setScale(novaSkala);
        view.repaint();

    }
}
