package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.Workspace;

public interface GraffTree {
    /// generisemo drvo
    GraffTreeView generateTree(Workspace workspace);
    /// dodajemo decu na korenski/roditeljski cvor
    void addChild(GraffTreeItem parent);
    /// getter za selektovani node
    GraffTreeItem getSelectenNode();
}
