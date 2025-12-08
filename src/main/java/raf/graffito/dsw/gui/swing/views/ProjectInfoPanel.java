package raf.graffito.dsw.gui.swing.views;

import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class ProjectInfoPanel extends JPanel implements Subscriber {
    private JLabel presentationLabel;
    private JLabel projectLabel;
    private JLabel authorLabel;
    private Project currentProject;
    private Presentation currentPresentation;

    public ProjectInfoPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Informacije o projektu"));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 14);
        Font boldFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel presPanel = createInfoRow("Prezentacija:", labelFont);
        presentationLabel = new JLabel("-");
        presentationLabel.setFont(boldFont);
        presPanel.add(presentationLabel);
        infoPanel.add(presPanel);
        infoPanel.add(Box.createVerticalStrut(10));

        JPanel projPanel = createInfoRow("Projekat:", labelFont);
        projectLabel = new JLabel("-");
        projectLabel.setFont(boldFont);
        projPanel.add(projectLabel);
        infoPanel.add(projPanel);
        infoPanel.add(Box.createVerticalStrut(10));

        JPanel authorPanel = createInfoRow("Autor:", labelFont);
        authorLabel = new JLabel("-");
        authorLabel.setFont(boldFont);
        authorPanel.add(authorLabel);
        infoPanel.add(authorPanel);
        infoPanel.add(Box.createVerticalStrut(10));

        infoPanel.add(Box.createVerticalStrut(20));

        add(infoPanel, BorderLayout.NORTH);
    }

    private JPanel createInfoRow(String text, Font font) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label = new JLabel(text);
        label.setFont(font);
        panel.add(label);
        return panel;
    }

    public void setCurrentProject(Project project) {
        // Ukloni subscriber sa starog projekta
        if (currentProject != null) {
            currentProject.removeSubscriber(this);
        }

        this.currentProject = project;

        // Registruj subscriber na novom projektu
        if (currentProject != null) {
            currentProject.addSubscriber(this);
        }

        Presentation pres = null;
        if(currentProject!=null && currentProject.getChildren().size() > 0) {
            for(int i = currentProject.getChildren().size() - 1; i >= 0; i--) {
                if(currentProject.getChildren().get(i) instanceof Presentation) {
                    pres = (Presentation) currentProject.getChildren().get(i);
                    break;
                }
            }
        }
        setCurrentPresentation(pres);
    }

    public void setCurrentPresentation(Presentation presentation) {
        if (currentPresentation != null) {
            currentPresentation.removeSubscriber(this);
        }
        this.currentPresentation = presentation;
        if (currentPresentation != null) {
            currentPresentation.addSubscriber(this);
        }
        updateDisplay();
    }

    private void updateDisplay() {
        presentationLabel.setText("-");
        projectLabel.setText("-");
        authorLabel.setText("-");

        if(currentProject != null) {
            projectLabel.setText(currentProject.getName());
            authorLabel.setText(currentProject.getAuthor());
        }

        if(currentPresentation != null) {
            presentationLabel.setText(currentPresentation.getName());
        }
    }

    @Override
    public void update(Object notification) {
        SwingUtilities.invokeLater(() -> updateDisplay());
    }

    public void clear() {
        if (currentProject != null) {
            currentProject.removeSubscriber(this);
        }
        if (currentPresentation != null) {
            currentPresentation.removeSubscriber(this);
        }

        currentProject = null;
        currentPresentation = null;
        updateDisplay();
    }
}
