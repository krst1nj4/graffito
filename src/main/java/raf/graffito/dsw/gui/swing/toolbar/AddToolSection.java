package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.factory.ImageElementFactory;
import raf.graffito.dsw.core.graff.factory.LogoElementFactory;
import raf.graffito.dsw.core.graff.factory.SlideElementFactory;
import raf.graffito.dsw.core.graff.factory.TextElementFactory;
import raf.graffito.dsw.core.graff.state.AddState;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.views.SlideView;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;

public class AddToolSection extends JToolBar {

    private SlideView slideView;
    private StateManager stateManager;

    public AddToolSection(StateManager stateManager) {
        super(JToolBar.VERTICAL);
        setFloatable(false);
        this.stateManager = stateManager;

        JButton btnText = new JButton(loadIcon("/images/tekstSlikaGraffito.jpg"));
        btnText.setToolTipText("Add Text");
        btnText.addActionListener(e -> dodajElement(new TextElementFactory()));

        JButton btnImage = new JButton(loadIcon("/images/IMG_0200.PNG"));
        btnImage.setToolTipText("Add Image");
        btnImage.addActionListener(e -> dodajElement(new ImageElementFactory()));

            JButton btnLogo = new JButton(loadIcon("/images/LogoZaGraffito.png"));
            btnLogo.setToolTipText("Add Logo");
            btnLogo.addActionListener(e -> dodajElement(new LogoElementFactory()));

            add(btnText);
            add(btnImage);
            add(btnLogo);
    }

    private Icon loadIcon(String path) {
        try{
            URL url = getClass().getResource(path);
            if(url == null){
                System.err.println("Slika nije pronadjena" + path);
                return null;
            }
            BufferedImage img = ImageIO.read(url);
            Image scaled = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    public void setCurrentView(SlideView slideView) {
        this.slideView = slideView;
    }

    private void dodajElement(SlideElementFactory factory){
        if (slideView == null) {
            JOptionPane.showMessageDialog(this, "Nije selektovan slajd!");
            return;
        }

        stateManager.setAddState();

        AddState addState = stateManager.getAddState();
        addState.setFactory(factory);

        addState.misKliknut(-1, -1, slideView);

    }

}
