package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.dialogs.AboutUsDialog;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractGraffAction{
    public  AboutUsAction() {
        putValue(SHORT_DESCRIPTION, "About us Page");
        putValue(NAME, "About us");
        putValue(SMALL_ICON, loadIcon("/images/aboutusicon.jpeg"));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsDialog view = new AboutUsDialog();
        view.setVisible(true);
    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender);
    }
}
