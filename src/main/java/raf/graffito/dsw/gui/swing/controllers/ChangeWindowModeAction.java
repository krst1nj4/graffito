package raf.graffito.dsw.gui.swing.controllers;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeWindowModeAction extends AbstractGraffAction {

    private String mode;

    public ChangeWindowModeAction(String mode) {
        putValue(NAME, mode);
        putValue(SHORT_DESCRIPTION, "Change window mode to " + mode);
        this.mode = mode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (mode) {
            case "Normal":
                MainFrame.getInstance().setModeNormal();
                break;
            case "Small":
                MainFrame.getInstance().setModeSmall();
                break;
            case "Fullscreen":
                MainFrame.getInstance().setModeFullScreen();
                break;
        }
    }
}
