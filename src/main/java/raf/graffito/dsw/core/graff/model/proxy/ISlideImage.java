package raf.graffito.dsw.core.graff.model.proxy;

import java.awt.*;

public interface ISlideImage {
    void paint(Graphics2D g, int width, int height);
    int getWidth();
    int getHeight();
    String getFilePath();
}
