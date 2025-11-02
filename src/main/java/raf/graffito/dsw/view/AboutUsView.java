package raf.graffito.dsw.view;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class AboutUsView extends JFrame{

    private JLabel text;
    private JLabel image;

    public AboutUsView() {
        setTitle("About us ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        text = new JLabel("TEST TEST TEST TEST TEST TEST", SwingConstants.CENTER);


        add(text, BorderLayout.SOUTH);

        image = new JLabel("TEST2 TEST2 TEST2 TEST2 TEST2", SwingConstants.CENTER);
        add(image, BorderLayout.NORTH);
    }

    public void setText(JLabel text) {
        this.text = text;
    }

    public void setImage(JLabel image) {
        this.image = image;
    }
}
