package raf.graffito.dsw.gui.swing.views;

import lombok.Getter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
@Getter


public class ProjectView extends JPanel implements Subscriber {
    private Project project;
    private JTabbedPane tabbedPane;
    private ProjectInfoPanel projectInfoPanel;
    private Color tabColor;
    private StateManager stateManager;

    public ProjectView(Project project, Color tabColor) {
        this.project = project;
        this.project.addSubscriber(this);
        this.tabColor = tabColor;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        projectInfoPanel = new ProjectInfoPanel();
        add(projectInfoPanel, BorderLayout.EAST);

        projectInfoPanel.setPreferredSize(new Dimension(150, 0));
        projectInfoPanel.setMinimumSize(new Dimension(50, 0));
        stateManager = new StateManager();

        if (project.getChildren() != null) {

        for (GraffNode node : project.getChildren()) {
            if (node instanceof Presentation p) {
                openPresentationTab(p);
            } else if (node instanceof Slide s) {
                openSlideTab(s);
            }
        }
    }


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();
                if (index != -1) {
                    Component comp = tabbedPane.getComponentAt(index);
                    if (comp instanceof PresentationView view) {
                        projectInfoPanel.setCurrentPresentation(view.getPresentation());
                    }
                } else {
                    projectInfoPanel.setCurrentPresentation(null);
                }
            }
        });

        for(Object pres : project.getChildren()){
            if(pres instanceof Presentation){
                openPresentationTab((Presentation) pres);
            }
        }
    }

    private void openPresentationTab(Presentation presentation) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component comp = tabbedPane.getComponentAt(i);
            if (comp instanceof PresentationView view && view.getPresentation() == presentation) {
                tabbedPane.setSelectedIndex(i);
                return;
            }
        }

        PresentationView view = new PresentationView(presentation);
        tabbedPane.addTab(presentation.getName(), view);
        int index = tabbedPane.getTabCount() - 1;
        tabbedPane.setBackgroundAt(index, tabColor);
        tabbedPane.setSelectedComponent(view);

        if (tabbedPane.getSelectedComponent() == view) {
            projectInfoPanel.setCurrentPresentation(presentation);
        }

        presentation.addSubscriber(this);
    }

    private void closePresentationTab(Presentation presentation) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component comp = tabbedPane.getComponentAt(i);
            if (comp instanceof PresentationView view && view.getPresentation() == presentation) {
                tabbedPane.removeTabAt(i);
                break;
            }
        }
        projectInfoPanel.setCurrentPresentation(getSelectedPresentation());
    }

//    private void openSlideTab(Slide slide) {
//        SlideEditorPanel editor = new SlideEditorPanel(slide, this.stateManager);
//        tabbedPane.addTab(slide.getName(), editor);
//        tabbedPane.setSelectedComponent(editor);
//    }

    private void openSlideTab(Slide slide) {
        SlideEditorPanel editor = new SlideEditorPanel(slide, this.stateManager);
        tabbedPane.addTab(slide.getName(), editor);
        tabbedPane.setSelectedComponent(editor);
    }

    public void closeSlideTab(Slide slide) {
        for(int i = 0; i < tabbedPane.getTabCount(); i++){
            Component comp = tabbedPane.getComponentAt(i);
            if(comp instanceof SlideView sv && sv.getSlide().equals(slide)) {
                tabbedPane.removeTabAt(i);
                break;
            }
        }
    }

    public Presentation getSelectedPresentation() {
        int index = tabbedPane.getSelectedIndex();
        if (index != -1) {
            Component comp = tabbedPane.getComponentAt(index);
            if (comp instanceof PresentationView view) return view.getPresentation();
        }
        return null;
    }

    private void refreshTabTitles() {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            Component comp = tabbedPane.getComponentAt(i);
            if (comp instanceof PresentationView view) {
                tabbedPane.setTitleAt(i, view.getPresentation().getName());
            }
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof Presentation presentation){
            if(project.getChildren().contains(presentation)){
                openPresentationTab(presentation);
            } else {
                closePresentationTab(presentation);
            }
        }else if(notification instanceof Slide slide){
            if(project.getChildren().contains(slide)){
                openSlideTab(slide);
            } else {
                closeSlideTab(slide);
            }
        } else if (notification.equals("projectDeleted")) {
            tabbedPane.removeAll();
            SwingUtilities.invokeLater(() -> projectInfoPanel.setCurrentProject(null));
        } else if (notification instanceof Project proj && proj == project) {
            projectInfoPanel.setCurrentProject(project);
            refreshTabTitles();
        } else if (notification.equals("presentationNameChanged")) {
            refreshTabTitles();
        }
    }

    public Slide getCurrentSlide() {
        int index =  tabbedPane.getSelectedIndex();

        if(index == -1) return null;

        Component comp = tabbedPane.getComponentAt(index);

        if (comp instanceof SlideEditorPanel) {
            return ((SlideEditorPanel) comp).getSlide();
        }

        if(comp  instanceof PresentationView){
            return ((PresentationView) comp).getCurrentSlide();
        }

        return null;
    }
}
