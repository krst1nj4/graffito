package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.SelectState;
import raf.graffito.dsw.core.graff.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class DesniToolbar extends JPanel {

        public DesniToolbar(StateManager  stateManager, Slide slide, SelectState selectState) {

            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(150 ,0));

            AddToolSection addToolSection = new AddToolSection(stateManager, slide);
            JScrollPane scrollPane = new JScrollPane(addToolSection);

            ActionToolSection  actionToolSection = new ActionToolSection(stateManager, slide, selectState);

            add(scrollPane, BorderLayout.CENTER);
            add(actionToolSection, BorderLayout.SOUTH);

        }
}
