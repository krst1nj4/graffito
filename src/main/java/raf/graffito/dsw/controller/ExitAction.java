package raf.graffito.dsw.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGraffAction {
    public ExitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
        putValue(SMALL_ICON, loadIcon("/images/exiticon2.png"));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}