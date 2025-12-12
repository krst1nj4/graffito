package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.ImageElement;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageElementFactory implements SlideElementFactory{

    private final BufferedImage image;

    public ImageElementFactory() {
        this.image = loadTestImage();
    }

    @Override
    public SlideElement createSlideElement(Slide slide) {
        return new ImageElement(slide, "image", 50, 50, image.getWidth()/3, image.getHeight()/3, image);
    }

    private BufferedImage loadTestImage() {
        try{
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/IMG_0200.PNG")));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
