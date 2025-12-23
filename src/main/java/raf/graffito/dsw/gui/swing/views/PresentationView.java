package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.controllers.SlideController;
import raf.graffito.dsw.gui.swing.toolbar.DesniToolbar;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

@Getter
public class PresentationView extends JPanel implements Subscriber {

    private Presentation presentation;
    private JPanel slidesPanel;
    private SlideView slideView;
    private JPanel centerPanel;
    private DesniToolbar desniToolbar;
    private StateManager stateManager;

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        this.presentation.addSubscriber(this);
        this.stateManager = new StateManager();
        initializeUI();
        loadSlides();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        slidesPanel = new JPanel();
        slidesPanel.setLayout(new BoxLayout(slidesPanel, BoxLayout.Y_AXIS));
        slidesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(slidesPanel);

        centerPanel = new JPanel(new  BorderLayout());
        centerPanel.setBackground(MainFrame.getInstance().getActionManager().getOpenProjectAction().getSelectedColor());

        desniToolbar = new DesniToolbar(stateManager);
        desniToolbar.setPreferredSize(new Dimension(150, 0));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, centerPanel);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);
        add(desniToolbar, BorderLayout.EAST);
    }

    private void loadSlides(){
        slidesPanel.removeAll();

        if(presentation.getChildren().isEmpty()){
            JLabel emptyLabel = new JLabel("Presentation has no slides. Create new using 'Add Node' button.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setForeground(Color.GRAY);
            slidesPanel.add(emptyLabel);
        }else {
            for (int i = 0; i < presentation.getChildren().size(); i++) {
                if (presentation.getChildren().get(i) instanceof Slide) {
                    Slide slide = (Slide) presentation.getChildren().get(i);
                    JPanel slidePanel = createSlidePanel(slide, i + 1);
                    slidesPanel.add(slidePanel);
                    slidesPanel.add(Box.createVerticalStrut(10));
                }
            }
        }

        slidesPanel.revalidate();
        slidesPanel.repaint();
    }

    private JPanel createSlidePanel(Slide slide, int index) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(MainFrame.getInstance().getActionManager().getOpenProjectAction().getSelectedColor());
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel slideLabel = new JLabel(index + ". " + slide.getName());
        slideLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(slideLabel, BorderLayout.NORTH);

        JLabel placeholderLabel = new JLabel("<Open slide here>");
        placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        placeholderLabel.setForeground(Color.GRAY);
        panel.add(placeholderLabel, BorderLayout.CENTER);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                openSlide(slide);
            }
        });

        return panel;
    }

    private void openSlide(Slide slide) {
        slideView = new SlideView(slide, stateManager);

        desniToolbar.updateCurrentSlideView(this.slideView);

        stateManager.setSelectState();
        centerPanel.removeAll();
        centerPanel.add(slideView, BorderLayout.CENTER);


        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void refresh() {
        loadSlides();
    }

    @Override
    public void update(Object notification) {
        loadSlides();
    }
}
