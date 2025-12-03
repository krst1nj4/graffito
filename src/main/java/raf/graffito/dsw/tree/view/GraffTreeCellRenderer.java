package raf.graffito.dsw.tree.view;

import lombok.NoArgsConstructor;
import raf.graffito.dsw.tree.model.GraffTreeItem;
import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

@NoArgsConstructor

public class GraffTreeCellRenderer extends DefaultTreeCellRenderer {
    /**
     * Klasa koju koristimo za prilagodjavanje izgleda ikonice u zavisnosti da li je
     * Workspace, Project, Presentation ili Slide
     *
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if(((GraffTreeItem)value).getGraffNode() instanceof Workspace) {
            imageURL = getClass().getResource("/images/workspaceicon.png");
        }

        else if(((GraffTreeItem)value).getGraffNode() instanceof Project) {
            imageURL = getClass().getResource("/images/projecticon.png");
        }

        else if(((GraffTreeItem)value).getGraffNode() instanceof Presentation) {
            imageURL = getClass().getResource("/images/presentationicon.png");
        }

        else if(((GraffTreeItem)value).getGraffNode() instanceof Slide) {
            imageURL = getClass().getResource("/images/slideicon.png");
        }

        Icon icon = null;
        if(imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        setIcon(icon);

        return this;
    }
}
