package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.toolbar.DesniToolbar;

import javax.swing.*;
import java.awt.*;

public class SlideEditorPanel extends JPanel {
    @Getter
    private SlideView slideView;
    private DesniToolbar desniToolbar;

    public SlideEditorPanel(Slide slide, StateManager stateManager) {
        setLayout(new BorderLayout());
        this.slideView = new SlideView(slide, stateManager);
        this.desniToolbar = new DesniToolbar(stateManager);

        this.desniToolbar.updateCurrentSlideView(this.slideView);

        add(slideView, BorderLayout.CENTER);
        add(desniToolbar, BorderLayout.EAST);
    }

    public Slide getSlide() {
        return slideView.getSlide();
    }
}
