package raf.graffito.dsw.core.graff.view;

import raf.graffito.dsw.core.graff.model.LogoElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LogoPainter {

    private LogoElement logoElement;

    public LogoPainter(LogoElement logoElement) {
        this.logoElement = logoElement;
    }

    public void paint(Graphics2D g) {

        int x = logoElement.getX();
        int y = logoElement.getY();
        int width = logoElement.getWidth();
        int height = logoElement.getHeight();

        AffineTransform old = g.getTransform();

        g.rotate(logoElement.getRotation(), x + width / 2.0, y + height / 2.0);

        g.setColor(new Color(255, 230, 0));
        g.fillOval(x, y, width, height);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3f));

        int eyeW = width/8;
        int eyeH = height/8;
        g.fillOval(x + width/4- eyeW/2, y + height/3 - eyeH/2, eyeW, eyeH);
        g.fillOval(x + 3*width/4 - eyeW/2, y + height/3 - eyeH/2, eyeW, eyeH);

        g.drawArc(x + width/4 + height/2,y + height/2, width/4, height/3, 200, 140);

        g.drawArc(x + width/2, y + height/2, width/4, height/3, 200, -140);

        g.setTransform(old);
    }
}
