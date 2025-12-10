package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.factory.ImageElementFactory;
import raf.graffito.dsw.core.graff.factory.LogoElementFactory;
import raf.graffito.dsw.core.graff.factory.TextElementFactory;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.AddState;
import raf.graffito.dsw.core.graff.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class AddToolSection extends JPanel {

    public AddToolSection(StateManager stateManager, Slide slide) {

        setLayout(new GridLayout(0, 1));
        setBackground(new Color(240, 240, 240));

        JButton btnText = new JButton("Add Text");
        btnText.addActionListener(e -> stateManager.setAddState(new AddState(slide, new TextElementFactory())));

        JButton btnImage = new JButton("Add Image");
        btnImage.addActionListener(e -> {
            ImageElementFactory factory = new ImageElementFactory();});
            stateManager.setAddState(new AddState(slide, new ImageElementFactory()));

            JButton btnLogo = new JButton("Add Logo");
            btnLogo.addActionListener(e -> stateManager.setAddState(new AddState(slide, new LogoElementFactory())));

            add(btnText);
            add(btnImage);
            add(btnLogo);
    }

}
