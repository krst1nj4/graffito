package raf.graffito.dsw.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public abstract class AbstractGraffAction extends AbstractAction {

    private Image originalImage;

    public Icon loadIcon(String path) {
        Icon icon = null;
        URL ImageURL = getClass().getResource(path); // URL služi za pronalaženje resursa unutar JAR fajla ili klase
        if (ImageURL != null) {
            Image img = new ImageIcon(ImageURL).getImage(); // Napravimo Image iz ImageIcon
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File " + path + " not found");
        }
        return icon;
    }

    public void setIconSize(int size) {
        if (originalImage != null) {
            Image scaled = originalImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            putValue(SMALL_ICON, new ImageIcon(scaled));
        }
    }

    public AbstractGraffAction() {}


    public abstract void actionPerformed(ActionEvent e);
}