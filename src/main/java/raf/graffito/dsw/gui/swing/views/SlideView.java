package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SlideView extends JPanel implements Subscriber {

    private Slide slide;
    private double scale = 1.0;

    public SlideView(Slide slide) {
        this.slide = slide;
        slide.addSubscriber(this);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void update(Object notification) {

    }
}
