package raf.graffito.dsw.gui.swing.controllers;

import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.*;

public class GraffMouseController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final SlideView view;
    private final StateManager stateManager;

    public GraffMouseController(SlideView view, StateManager stateManager) {
        this.view = view;
        this.stateManager = stateManager;
    }

    private double transformX(int x){ return x / view.getScale(); }
    private double transformY(int y){ return y / view.getScale(); }

    @Override
    public void mouseClicked(MouseEvent e) {
       // stateManager.getCurrent().misKliknut(transformX(e.getX()), transformY(e.getY()), view);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        stateManager.getCurrent().misKliknut(transformX(e.getX()), transformY(e.getY()), view);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        stateManager.getCurrent().misOtpusten(transformX(e.getX()), transformY(e.getY()), view);
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.getCurrent().misPovucen(transformX(e.getX()), transformY(e.getY()), view);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        stateManager.getCurrent().misSkrolovan(e.getPreciseWheelRotation(), view);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
