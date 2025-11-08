package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.Workspace;
import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;

import javax.swing.tree.DefaultTreeModel;

public class GraffTreeImplements implements GraffTree {

    private GraffTreeView graffTreeView;
    private DefaultTreeModel treeModel;

    @Override
    public GraffTreeView generateTree(Workspace workspace) {
        /// kreiramo korenski cvor stabla koji je kod nas workspace
        GraffTreeItem koren = new GraffTreeItem(workspace);
        /// kreiramo model stabla sa korenskim cvorom
        treeModel = new DefaultTreeModel(koren);
        /// kreiramo view stabla sa modelom
        graffTreeView = new GraffTreeView(treeModel);


        return graffTreeView;
    }

    @Override
    public void addChild(GraffTreeItem parent) {
        if(!((parent.getGraffNode()) instanceof GraffNodeComposite)) {
            return;
        }



    }

    @Override
    public GraffTreeItem getSelectenNode() {
        return null;
    }

}
