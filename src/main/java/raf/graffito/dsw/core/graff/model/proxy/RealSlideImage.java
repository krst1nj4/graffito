package raf.graffito.dsw.core.graff.model.proxy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RealSlideImage implements ISlideImage{

    private BufferedImage bufferedImage;
    private String filePath;

    /// Konstruktor za proxy
    public RealSlideImage(String filePath) {
        this.filePath = filePath;
        loadImage();
    }

    public RealSlideImage(BufferedImage image) {
        this.bufferedImage = image;
        this.filePath = "Internal Resource";
    }

    private void loadImage() {
        try {
            System.out.println("Učitavam sliku sa diska: " + filePath);
            this.bufferedImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Neuspešno učitavanje slike: " + filePath);
        }
    }

    @Override
    public void paint(Graphics2D g, int width, int height) {
        if(bufferedImage != null){
            g.drawImage(bufferedImage, 0, 0, width, height, null);
        }
    }

    @Override
    public int getWidth() {
        return bufferedImage != null ? bufferedImage.getWidth() : 0;
    }

    @Override
    public int getHeight() {
        return bufferedImage != null ? bufferedImage.getHeight() : 0;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
