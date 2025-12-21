package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.SelectState;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.views.SlideView;

import javax.swing.*;
import java.awt.*;

public class DesniToolbar extends JToolBar {

    private ActionToolSection actionSection;
    private AddToolSection addSection;

    public DesniToolbar(StateManager stateManager) {
        super(JToolBar.VERTICAL);
        setFloatable(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        actionSection = new ActionToolSection(stateManager);
        addSection = new AddToolSection(stateManager);

        // Dodajemo komponente u JToolBar
        add(new JLabel(" ALATI "));
        add(actionSection);
        addSeparator();

        add(new JLabel(" DODAVANJE "));
        add(addSection);
        addSeparator();
    }

    public void updateCurrentSlideView(SlideView slideView) {
        actionSection.setCurrentView(slideView);
    }
}
