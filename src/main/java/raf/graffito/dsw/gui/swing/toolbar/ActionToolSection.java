package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.RotateState;
import raf.graffito.dsw.core.graff.state.SelectState;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.views.SlideView;

import javax.swing.*;
import java.awt.*;

public class ActionToolSection extends JToolBar {

    private StateManager stateManager;
    private SlideView slideView;

    public ActionToolSection(StateManager stateManager) {
        super(JToolBar.VERTICAL);
        this.stateManager = stateManager;
        setFloatable(false);
        initButtons();
    }

    private void initButtons() {
        SlideView currentView = slideView;

        // SELECT
        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(e -> stateManager.setSelectState());
        add(btnSelect);

        // MOVE
        JButton btnMove = new JButton("Move");
        btnMove.addActionListener(e -> stateManager.setMoveState());
        add(btnMove);

        // ROTATE LEFT (Rotacija ulevo)
        JButton btnRotL = new JButton("Rot L");
        btnRotL.addActionListener(e -> {
            stateManager.setRotateState();
            stateManager.getRotateState().setUSmeruKazaljke(false);
            if (currentView != null) {
                stateManager.getRotateState().izvrsiRotaciju(currentView);
            }
        });
        add(btnRotL);

        // ROTATE RIGHT (Rotacija udesno)
        JButton btnRotR = new JButton("Rot R");
        btnRotR.addActionListener(e -> {
            stateManager.setRotateState();
            stateManager.getRotateState().setUSmeruKazaljke(true);
            if (currentView != null) {
                stateManager.getRotateState().izvrsiRotaciju(currentView);
            }
        });
        add(btnRotR);

        // DELETE (Odmah briše selektovano)
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            stateManager.setDeleteState();
            if (currentView != null) {
                stateManager.getDeleteState().izbrisiSelektovane(currentView);
            }
        });
        add(btnDelete);

        // ZOOM
        JButton btnZoom = new JButton("Zoom");
        btnZoom.addActionListener(e -> stateManager.setZoomState());
        add(btnZoom);
    }

    public void setCurrentView(SlideView slideView) {
        this.slideView = slideView;
    }
}
