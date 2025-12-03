package raf.graffito.dsw.tree.model;

import raf.graffito.dsw.tree.view.GraffTreeView;
import raf.graffito.dsw.core.graff.model.Workspace;

public interface GraffTree {
    /// generisemo drvo
    GraffTreeView generateTree(Workspace workspace);
    /// dodajemo decu na korenski/roditeljski cvor
    void addChild(GraffTreeItem parent);
    /// getter za selektovani node
    GraffTreeItem getSelectedNode();
}
