package raf.graffito.dsw.tree.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.tree.view.GraffTreeView;
import raf.graffito.dsw.core.graff.model.Workspace;

import javax.swing.tree.DefaultMutableTreeNode;

public interface GraffTree {
    /// generisemo drvo
    GraffTreeView generateTree(Workspace workspace);
    /// dodajemo decu na korenski/roditeljski cvor
    ///void addChild(GraffTreeItem parent);

    void addChild(GraffTreeItem parent, GraffNode child);

    /// getter za selektovani node
    DefaultMutableTreeNode getSelectedNode();

    boolean createChild(DefaultMutableTreeNode node);
    boolean deleteChild(DefaultMutableTreeNode node);
    void refreshTree();

}
