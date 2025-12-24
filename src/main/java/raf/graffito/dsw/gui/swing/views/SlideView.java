package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.controllers.GraffMouseController;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SlideView extends JPanel implements Subscriber {

    private Slide slide;
    private double scale = 1.0;
    private StateManager stateManager;

    public SlideView(Slide slide, StateManager stateManager) {
        this.slide = slide;
        this.stateManager =  stateManager;
        slide.addSubscriber(this);
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        GraffMouseController controller = new GraffMouseController(this, stateManager);
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
        this.addMouseWheelListener(controller);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (slide == null) return;

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.scale(scale, scale);
        //ivica:
        g2d.setBackground(Color.WHITE);
        g2d.fillRect(0, 0, 800, 600);

        g2d.setColor(Color.BLUE);
        g2d.drawRect(0, 0, 800, 600);
        for (GraffNode node : slide.getChildren()){
            if(node instanceof SlideElement el){
                el.paint(g2d);
            }
        }

        if (stateManager.getSelectState().getLasoPravougaonik() != null) {
            g2d.setColor(new Color(100, 150, 255, 100));
            g2d.fill(stateManager.getSelectState().getLasoPravougaonik());
            g2d.setColor(Color.BLUE);
            g2d.draw(stateManager.getSelectState().getLasoPravougaonik());
        }

        g2d.dispose();
    }

    @Override
    public void update(Object notification) {
        repaint();
    }
}
