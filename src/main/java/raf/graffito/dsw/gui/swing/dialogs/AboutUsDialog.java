package raf.graffito.dsw.gui.swing.dialogs;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class AboutUsDialog extends JDialog{



    public AboutUsDialog() {
        setTitle("About us ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
}
