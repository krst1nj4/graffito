package raf.graffito.dsw.tree.controller;

import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class GraffTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        GraffTreeItem graffTreeItem = (GraffTreeItem)path.getLastPathComponent();
        System.out.println("Selektovan cvor: " + graffTreeItem.getGraffNode());
        System.out.println("Path: " + path);
    }
}
