package raf.graffito.dsw.view;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class AboutUsView extends JFrame{

    public AboutUsView() {
        setTitle("About us ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2,50,0));

        ImageIcon image = new ImageIcon("src/main/resources/images/IMG_0200.PNG");
        ImageIcon image2 = new ImageIcon("src/main/resources/images/krstinja.png");


        add(osoba(image, "Dimitrije Stanojevic 4924 RN"));
        add(osoba(image2, "Krstinja Kostic 2924 RN"));
    }

    private JPanel osoba(ImageIcon image, String ime) {
        JPanel panel = new JPanel(new BorderLayout(20, 20));

        Image slika = image.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        JLabel slikaLabel = new JLabel(new  ImageIcon(slika), JLabel.CENTER);

        JLabel imeLabel = new JLabel(ime,  JLabel.CENTER);

        panel.add(slikaLabel, BorderLayout.CENTER);
        panel.add(imeLabel,  BorderLayout.SOUTH);

        return panel;
    }
//    public void setText(JLabel text) {
//        this.text = text;
//    }

//    Dimension labelSize = imageLabel.getSize(); // Or JFrame.getSize()
//    int newWidth = labelSize.width;
//    int newHeight = labelSize.height;
//
//    // Maintain aspect ratio if desired
//    // Example: calculate newHeight based on aspect ratio
//    // double aspectRatio = (double) originalImage.getWidth(null) / originalImage.getHeight(null);
//    // newHeight = (int) (newWidth / aspectRatio);
//
//    Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//    ImageIcon scaledIcon = new ImageIcon(scaledImage);
//    imageLabel.setIcon(scaledIcon);
//
//    public void setImage(JLabel image) {
//        this.image = image;
//    }
}
