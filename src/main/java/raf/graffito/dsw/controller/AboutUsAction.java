package raf.graffito.dsw.controller;

import raf.graffito.dsw.view.AboutUsView;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractGraffAction{
    public  AboutUsAction() {
        putValue(SHORT_DESCRIPTION, "About us Page");
        putValue(NAME, "About us");
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsView view = new AboutUsView();
        view.setVisible(true);
    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender);
    }
}
