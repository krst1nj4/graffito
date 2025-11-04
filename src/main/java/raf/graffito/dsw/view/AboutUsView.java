package raf.graffito.dsw.view;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class AboutUsView extends JFrame{

    private JLabel imageMita;
    private JLabel imageKrizz;
    private JLabel ime1;
    private JLabel ime2;
    private JLabel indeks1;
    private JLabel indeks2;

    public AboutUsView() {
        setTitle("About us ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1));


        ime1 = new JLabel("Krstinja Kostic :3", SwingConstants.LEFT);
        ime2 = new JLabel("Dimitrije Stanojevic :3", SwingConstants.RIGHT);
        indeks1 = new JLabel("RN 29/2024", SwingConstants.LEFT);
        indeks2 = new JLabel("RN 49/2024", SwingConstants.RIGHT);

        //add(text, BorderLayout.SOUTH);
        southPanel.add(ime1);
        southPanel.add(ime2);
        southPanel.add(indeks1);
        southPanel.add(indeks2);

        add(southPanel, BorderLayout.SOUTH);

        ImageIcon mitaIcon =  new ImageIcon("src/main/resources/images/IMG_0200.PNG");
        Image ogMita = mitaIcon.getImage();

        int newWidth = 228;
        int newHeight = 171;

        Image scaledMita = ogMita.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledMitaIcon = new ImageIcon(scaledMita);

        JLabel mitaLabel = new JLabel(scaledMitaIcon);

        imageMita = new JLabel(mitaIcon);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(1,2));

        imagePanel.add(mitaLabel);
        add(imagePanel, BorderLayout.CENTER);

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
