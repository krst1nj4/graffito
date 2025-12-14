package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.RotateState;
import raf.graffito.dsw.core.graff.state.SelectState;
import raf.graffito.dsw.core.graff.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class ActionToolSection extends JPanel {

    public ActionToolSection(StateManager stateManager, Slide slide, SelectState selectState) {

        setLayout(new GridLayout(0, 1));
        setBackground(new Color(230, 230, 230));

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(e -> stateManager.setSelectState());

        JButton btnMove = new JButton("Move");
        btnMove.addActionListener(e -> {
            stateManager.setMoveState();
        });

        JButton btnResize = new JButton("Resize");
        btnResize.addActionListener(e -> {
            stateManager.setResizeState();
        });

        JButton btnRotateRight = new JButton("Rotate Right");
        btnRotateRight.addActionListener(e -> {
            RotateState rs = stateManager.getRotateState();
            rs.setClockwise(true);
            stateManager.setRotateState();
        });

        JButton btnRotateLeft = new JButton("Rotate Left");
        btnRotateLeft.addActionListener(e -> {
           RotateState rs = stateManager.getRotateState();
           rs.setClockwise(false);
           stateManager.setRotateState();
        });

        JButton btnZoom = new JButton("Zoom");
        btnZoom.addActionListener(e -> {stateManager.setZoomState();});

        add(btnSelect);
        add(btnMove);
        add(btnResize);
        add(btnRotateRight);
        add(btnRotateLeft);
        add(btnZoom);
    }
}
