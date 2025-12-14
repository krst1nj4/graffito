package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
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
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (slide == null) return;

        Graphics2D g2d = (Graphics2D) g.create();
        //Stavi scale
        g2d.scale(scale, scale);
        //crta belu stranicu sa ivicom
        g2d.setBackground(Color.WHITE);
        g2d.fillRect(0, 0, 800, 600);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(0, 0, 800, 600);
        //Crta sve slajd elemente
        for (GraffNode node : slide.getChildren()){
            if(node instanceof SlideElement el){
                el.paint(g2d);
            }
        }
        g2d.dispose();
    }

    @Override
    public void update(Object notification) {
        repaint();
    }
}
