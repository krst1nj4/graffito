package raf.graffito.dsw.gui.swing.controllers;


import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.views.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractGraffAction {


    public UndoAction() {
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo last action");
        // putValue(SMALL_ICON, loadIcon("images/undo.png")); // Otkomentarisi kad sredis slike
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Logika koju smo pisali
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if (projectView == null) return;

        Slide currentSlide = projectView.getCurrentSlide();
        if (currentSlide != null) {
            currentSlide.getKomandaManager().undo();
            MainFrame.getInstance().repaint();
        }

    }
}
