package raf.graffito.dsw.gui.swing.controllers;

import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.*;

public class SlideController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private  SlideView slideView;
    private  StateManager stateManager;

    public SlideController(SlideView slideView, StateManager stateManager) {
        this.slideView = slideView;
        this.stateManager = stateManager;
    }




    @Override
    public void mousePressed(MouseEvent e) {
        stateManager.getCurrent().mousePressed(e);
        slideView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.getCurrent().mouseDragged(e);
        slideView.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        stateManager.getCurrent().mouseWheelMoved(e);
        slideView.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}



    @Override
    public void mouseMoved(MouseEvent e) {}


}
