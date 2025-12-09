package raf.graffito.dsw.core.graff.state;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State {
    default void mousePressed(MouseEvent e) {}
    default void mouseReleased(MouseEvent e) {}
    default void mouseDragged(MouseEvent e) {}
    default void mouseWheelMoved(MouseWheelEvent e) {}
}
