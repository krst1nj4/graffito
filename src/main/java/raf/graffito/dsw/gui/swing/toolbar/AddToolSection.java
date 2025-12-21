package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.factory.ImageElementFactory;
import raf.graffito.dsw.core.graff.factory.LogoElementFactory;
import raf.graffito.dsw.core.graff.factory.TextElementFactory;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.AddState;
import raf.graffito.dsw.core.graff.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class AddToolSection extends JToolBar {

    public AddToolSection(StateManager stateManager) {
        super(JToolBar.VERTICAL);
        setFloatable(false);

        JButton btnText = new JButton("Add Text");
        btnText.addActionListener(e -> {
                stateManager.setAddState();
            ((AddState) stateManager.getCurrent()).setFactory(new TextElementFactory());
                });

        JButton btnImage = new JButton("Add Image");
        btnImage.addActionListener(e ->{
            stateManager.setAddState();
            ((AddState) stateManager.getCurrent()).setFactory(new ImageElementFactory());
        });

            JButton btnLogo = new JButton("Add Logo");
            btnLogo.addActionListener(e -> {
                stateManager.setAddState();
                ((AddState) stateManager.getCurrent()).setFactory(new LogoElementFactory());
            });

            add(btnText);
            add(btnImage);
            add(btnLogo);
    }

}
