package raf.graffito.dsw.tree.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.tree.view.GraffTreeView;
import raf.graffito.dsw.core.graff.model.Workspace;

import javax.swing.tree.DefaultMutableTreeNode;

public interface GraffTree {
    void loadProject(Project project);
    void generateTree(GraffNode root);
    void addChild(GraffTreeItem parent, GraffNode child);
    DefaultMutableTreeNode getSelectedNode();
    boolean createChild(DefaultMutableTreeNode node);
    boolean deleteChild(DefaultMutableTreeNode node);
    void refreshTree();

}
