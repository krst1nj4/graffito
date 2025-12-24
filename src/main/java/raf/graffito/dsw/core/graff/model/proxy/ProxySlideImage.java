package raf.graffito.dsw.core.graff.model.proxy;

import java.awt.*;

public class ProxySlideImage implements ISlideImage {
    private RealSlideImage realImage;
    private String filePath;

    public ProxySlideImage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void paint(Graphics2D g, int width, int height) {
        if (realImage == null) {
            realImage = new RealSlideImage(filePath);
        }
        realImage.paint(g, width, height);
    }

    @Override
    public int getWidth() {
        if (realImage == null) {
            realImage = new RealSlideImage(filePath);
        }
        return realImage.getWidth();
    }

    @Override
    public int getHeight() {
        if (realImage == null) {
            realImage = new RealSlideImage(filePath);
        }
        return realImage.getHeight();
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
