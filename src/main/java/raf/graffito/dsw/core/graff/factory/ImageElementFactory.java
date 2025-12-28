package raf.graffito.dsw.core.graff.factory;

import raf.graffito.dsw.core.graff.model.ImageElement;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.model.proxy.ISlideImage;
import raf.graffito.dsw.core.graff.model.proxy.ProxySlideImage;
import raf.graffito.dsw.core.graff.model.proxy.RealSlideImage;
import raf.graffito.dsw.core.graff.space.SpaceValidator;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageElementFactory implements SlideElementFactory{

    private final String imagePath;

    public ImageElementFactory(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageElementFactory(){
        this.imagePath = null;
    }

    @Override
    public SlideElement createSlideElement(Slide slide) {
        ISlideImage imageContent;
        int width = 200;
        int height = 150;

        boolean imaMesta = SpaceValidator.getInstance().checkSpace(slide, 50, 50, width, height);

        if (!imaMesta) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Nema dovoljno mesta za sliku!",
                    "Greska", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if(imagePath != null){
            imageContent = new ProxySlideImage(imagePath);
        } else {
            BufferedImage defaultImg = loadTestImage();
            if(defaultImg == null) return null;

            imageContent = new RealSlideImage(defaultImg);
        }

        return new ImageElement(slide, "Image", 50, 50, width, height, imageContent);
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
