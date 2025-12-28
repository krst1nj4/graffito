package raf.graffito.dsw.gui.swing.controllers;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.views.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractGraffAction {


    public RedoAction() {
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo last action");
        // Preclica CTRL + Y
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if (projectView == null) return;

        Slide currentSlide = projectView.getCurrentSlide();

        if (currentSlide != null) {
            currentSlide.getKomandaManager().redo();
        }
    }
}
