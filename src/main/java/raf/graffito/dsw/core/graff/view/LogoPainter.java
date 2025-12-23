package raf.graffito.dsw.core.graff.view;

import raf.graffito.dsw.core.graff.model.LogoElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

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

        double mouthW = width * 0.4;
        double mouthH = height * 0.15;
        double startX = x + width / 2.0 - mouthW / 2.0;
        double startY = y + height * 0.6;
        double midX = x + width / 2.0;
        double endX = x + width / 2.0 + mouthW / 2.0;

        Path2D.Double mouth = new Path2D.Double();
        mouth.moveTo(startX, startY);

        mouth.curveTo(
                startX + mouthW / 4, startY + mouthH,
                midX - mouthW / 4, startY + mouthH,
                midX, startY
        );

        mouth.curveTo(
                midX + mouthW / 4, startY + mouthH,
                endX - mouthW / 4, startY + mouthH,
                endX, startY
        );

        g.draw(mouth);

        g.setTransform(old);
    }
}
