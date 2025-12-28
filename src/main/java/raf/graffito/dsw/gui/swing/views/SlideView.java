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
import java.awt.geom.AffineTransform;

@Getter
@Setter
public class SlideView extends JPanel implements Subscriber {

    private Slide slide;
    private double scale = 1.0;
    private StateManager stateManager;

    public static final int LOGICAL_WIDTH = 1280;
    public static final int LOGICAL_HEIGHT = 720;

    private AffineTransform transform = new AffineTransform();

    public SlideView(Slide slide, StateManager stateManager) {
        this.slide = slide;
        this.stateManager =  stateManager;
        slide.addSubscriber(this);
        setPreferredSize(new Dimension(LOGICAL_WIDTH, LOGICAL_HEIGHT));
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

        double panelWidth = getWidth();
        double panelHeight = getHeight();

        double scaleX = panelWidth / LOGICAL_WIDTH;
        double scaleY = panelHeight / LOGICAL_HEIGHT;

        this.scale = Math.min(scaleX, scaleY) * 0.95;


        double scaledWidth = LOGICAL_WIDTH * this.scale;
        double scaledHeight = LOGICAL_HEIGHT * this.scale;

        double dx = (panelWidth - scaledWidth) / 2;
        double dy = (panelHeight - scaledHeight) / 2;

        g2d.translate(dx, dy);

        transform = new AffineTransform();
        transform.translate(dx, dy);
        transform.scale(scale, scale);

        g2d.transform(transform);
        //ivica:
        g2d.setBackground(Color.WHITE);
        g2d.fillRect(0, 0, LOGICAL_WIDTH, LOGICAL_HEIGHT);

        g2d.setColor(Color.BLUE);
        g2d.drawRect(0, 0, LOGICAL_WIDTH, LOGICAL_HEIGHT);
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
