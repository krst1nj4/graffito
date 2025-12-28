package raf.graffito.dsw.controller;

import lombok.Getter;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.decorator.ColorDecorator;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.views.ProjectView;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class OpenProjectAction extends AbstractGraffAction {
    @Getter
    private Color selectedColor;
    public OpenProjectAction() {
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open all presentations of the selected project");
       putValue(SMALL_ICON, loadIcon("/images/openprojecticon.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Uzmi selektovani čvor iz stabla
        TreePath path = ((JTree) MainFrame.getInstance().getGraffTree()).getSelectionPath();
        if (path == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(
                    MessageType.GRESKA,"You have to select a project!"
            );
            return;
        }

        Object lastComponent = path.getLastPathComponent();
        if (!(lastComponent instanceof GraffTreeItem)) {
            return;
        }

        GraffTreeItem treeItem = (GraffTreeItem) lastComponent;
        Object graffNode = treeItem.getGraffNode();

        // Proveri da li je selektovan projekat
        if (!(graffNode instanceof Project)) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(
                    MessageType.GRESKA,
                    "You have to select a project!"
            );
            return;
        }
        Project project = (Project) graffNode;
        if (MainFrame.getInstance().getProjectView() != null && MainFrame.getInstance().getProjectView().getProject().equals(project)) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(
                    MessageType.GRESKA,
                    "Project is already opened!"
            );
            return;
        }

        List<ColorDecorator> projectDecorators = ApplicationFramework.getInstance().getGraffRepository().getProjectDecorators();
        Set<Color> usedColors = ApplicationFramework.getInstance().getGraffRepository().getUsedColors();

        ColorDecorator decorator = projectDecorators.stream()
                .filter(projectDecorator ->  projectDecorator.getDecoratedNode().equals(graffNode))
                .findFirst()
                .orElse(null);

        if (decorator == null || decorator.getColor() == null) {
            selectedColor = JColorChooser.showDialog(null, "Choose color of the project", Color.CYAN);
            if(usedColors.contains(selectedColor)) {
                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(
                        MessageType.GRESKA,
                        "Color is already used!"
                );
                return;
            }

            decorator = new ColorDecorator(project, selectedColor);
            projectDecorators.add(decorator);
            usedColors.add(selectedColor);
        }

        Color projectColor = decorator.getColor();

        ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(
                MessageType.OBAVESTENJE,
                "Opened project " + project.getName()

        );
        MainFrame.getInstance().getGraffTree().refreshTree();
        MainFrame.getInstance().setProjectView(new ProjectView((Project) graffNode, projectColor));
        MainFrame.getInstance().getProjectView().getProjectInfoPanel().setCurrentProject((Project) graffNode);
    }
}