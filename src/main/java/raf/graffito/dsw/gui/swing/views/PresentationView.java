package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.gui.swing.controllers.SlideController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter

public class PresentationView extends JPanel {

    private Presentation presentation;
    private JPanel slidesPanel;
    private SlideView slideView;
    private JComponent rightPanel;

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        initializeUI();
        loadSlides();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        slidesPanel = new JPanel();
        slidesPanel.setLayout(new BoxLayout(slidesPanel, BoxLayout.Y_AXIS));
        slidesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(slidesPanel);

        rightPanel = new JPanel(new  BorderLayout());
        rightPanel.setPreferredSize(new Dimension(850, 650));


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, rightPanel);
        add(splitPane, BorderLayout.CENTER);
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

    private void openSlide(Slide slide) {
        slideView = new SlideView(slide);

        //update stateManager da zna view
        ProjectView pv = (ProjectView) SwingUtilities.getAncestorOfClass(ProjectView.class, this);
        pv.getStateManager().setSelectState();
        pv.getStateManager().getSelectState().setSlide(slide);

        rightPanel.removeAll();
        rightPanel.add(slideView, BorderLayout.CENTER);

        slideView.addMouseListener(new SlideController(slideView, pv.getStateManager()));
        slideView.addMouseMotionListener(new SlideController(slideView, pv.getStateManager()));
        slideView.addMouseWheelListener(new SlideController(slideView, pv.getStateManager()));

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private JPanel createSlidePanel(Slide slide, int index) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel slideLabel = new JLabel(index + ". " + slide.getName());
        slideLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(slideLabel, BorderLayout.NORTH);

        JLabel placeholderLabel = new JLabel("<Slide here>");
        placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        placeholderLabel.setForeground(Color.GRAY);
        panel.add(placeholderLabel, BorderLayout.CENTER);

        return panel;
    }

    public void refresh() {
        loadSlides();
    }

}
