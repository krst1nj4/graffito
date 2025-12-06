package raf.graffito.dsw.tree.view;

import raf.graffito.dsw.tree.controller.GraffTreeCellEditor;
import raf.graffito.dsw.tree.controller.GraffTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeView extends JTree {
    public GraffTreeView(DefaultTreeModel treeModel) {
        setModel(treeModel);
        GraffTreeCellRenderer graffTreeCellRenderer = new GraffTreeCellRenderer();
        addTreeSelectionListener(new GraffTreeSelectionListener());
        setCellEditor(new GraffTreeCellEditor(this, graffTreeCellRenderer));
        setCellRenderer(graffTreeCellRenderer);
        setEditable(true);
    }
}
