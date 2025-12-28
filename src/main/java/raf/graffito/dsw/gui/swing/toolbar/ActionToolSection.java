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
            if (this.slideView != null) {
                stateManager.getRotateState().izvrsiRotaciju(this.slideView);
            }
        });
        add(btnRotL);

        // ROTATE RIGHT (Rotacija udesno)
        JButton btnRotR = new JButton("Rot R");
        btnRotR.addActionListener(e -> {
            stateManager.setRotateState();
            stateManager.getRotateState().setUSmeruKazaljke(true);
            if (this.slideView != null) {
                stateManager.getRotateState().izvrsiRotaciju(this.slideView);
            }
        });
        add(btnRotR);

        JButton btnResize = new JButton("Resize");
        btnResize.addActionListener(e -> {
            stateManager.setResizeState();
        });
        add(btnResize);

        // DELETE
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            stateManager.setDeleteState();
            if (this.slideView != null) {
                stateManager.getDeleteState().izbrisiSelektovane(this.slideView);
            }
            stateManager.setSelectState();
        });
        add(btnDelete);

        // ZOOM
        JButton btnZoom = new JButton("Zoom");
        btnZoom.addActionListener(e -> stateManager.setZoomState());
        add(btnZoom);

        JButton btnCopy = new JButton("Copy");
        btnCopy.addActionListener(e -> {
           stateManager.setCopyState();
           stateManager.getCopyState().copy();
           stateManager.setSelectState();
        });
        add(btnCopy);

        JButton btnPaste = new JButton("Paste");
        btnPaste.addActionListener(e -> {
            if(this.slideView != null) {
                stateManager.setPasteState();
                stateManager.getPasteState().paste(this.slideView);
                stateManager.setSelectState();
            }else{
                JOptionPane.showMessageDialog(this, "Nije selektovan slajd za nalepljivanje");
            }
        });
        add(btnPaste);

        addSeparator();
    }

    public void setCurrentView(SlideView slideView) {
        this.slideView = slideView;
    }
}
