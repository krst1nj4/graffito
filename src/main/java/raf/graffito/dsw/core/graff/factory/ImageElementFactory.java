package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.ImageElement;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageElementFactory implements SlideElementFactory{

    private final BufferedImage image;

    public ImageElementFactory(BufferedImage image) {
        this.image = image;
    }

    public ImageElementFactory(){
        this.image = loadTestImage();
    }

    @Override
    public SlideElement createSlideElement(Slide slide) {
        int width = image.getWidth();
        int height = image.getHeight();

        if(width > 400 ){
            double ratio = (double) width/height;
            width = 400;
            height = (int)(width/ratio);
        }
        return new ImageElement(slide, "image", 50, 50, width, height, image);
    }

    private BufferedImage loadTestImage() {
        try{
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/IMG_0200.PNG")));

        } catch (Exception e) {
            System.out.println("Nije pronadjena default slika");
            e.printStackTrace();
            return null;
        }

    }
}
