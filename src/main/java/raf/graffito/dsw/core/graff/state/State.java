package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.gui.swing.views.SlideView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State {
    default void misKliknut(double x, double y, SlideView view){}

    default void misPovucen(double x, double y, SlideView view){}

    default void misOtpusten(double x, double y, SlideView view){}

    default void misSkrolovan(double rotacija, SlideView view){}

    default void izvrsiDirektnuAkticju(SlideView view){}
}
