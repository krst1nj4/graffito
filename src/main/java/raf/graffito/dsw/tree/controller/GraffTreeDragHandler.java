package raf.graffito.dsw.tree.controller;

import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseListener;

public class GraffTreeDragHandler extends TransferHandler {
    private DataFlavor nodesFlavor;
    private DataFlavor[] flavors = new DataFlavor[1];

    public GraffTreeDragHandler() {
        try {
            String mimeType = DataFlavor.javaJVMLocalObjectMimeType +
                    ";class=\"" + GraffTreeItem.class.getName() + "\"";
            nodesFlavor = new DataFlavor(mimeType);
            flavors[0] = nodesFlavor;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound: " + e.getMessage());
        }
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTree tree = (JTree) c;
        TreePath path = tree.getSelectionPath();
        if (path != null) {
            GraffTreeItem node = (GraffTreeItem) path.getLastPathComponent();
            // Dozvoli drag samo za slajdove
            if (node.getGraffNode() instanceof Slide) {
                return new NodesTransferable(node);
            }
        }
        return null;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }

        support.setShowDropLocation(true);

        if (!support.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }

        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        TreePath dest = dl.getPath();

        if (dest == null) {
            return false;
        }

        GraffTreeItem target = (GraffTreeItem) dest.getLastPathComponent();

        // Proveri da li je drop lokacija validna (mora biti Presentation ili Slide unutar iste prezentacije)
        return target.getGraffNode() instanceof raf.graffito.dsw.core.graff.model.Presentation ||
                target.getGraffNode() instanceof Slide;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        // Ekstraktuj podatke iz transfera
        GraffTreeItem draggedNode;
        try {
            Transferable t = support.getTransferable();
            draggedNode = (GraffTreeItem) t.getTransferData(nodesFlavor);
        } catch (Exception e) {
            return false;
        }

        // Uzmi drop lokaciju
        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        int childIndex = dl.getChildIndex();
        TreePath dest = dl.getPath();
        GraffTreeItem parent = (GraffTreeItem) dest.getLastPathComponent();
        JTree tree = (JTree) support.getComponent();
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        // Uzmi stari parent
        GraffTreeItem oldParent = (GraffTreeItem) draggedNode.getParent();

        // Ako je drop na slide, uzmi njegov parent (presentation)
        if (parent.getGraffNode() instanceof Slide) {
            childIndex = parent.getParent().getIndex(parent);
            if (childIndex != -1) {
                childIndex++; // Ubaci posle targeta
            }
            parent = (GraffTreeItem) parent.getParent();
        }

        // Proveri da li je move unutar iste prezentacije
        if (oldParent != parent) {
            return false;
        }

        // Ukloni iz starog parent-a
        int oldIndex = oldParent.getIndex(draggedNode);
        ((GraffNodeComposite) oldParent.getGraffNode()).removeChild(draggedNode.getGraffNode());
        model.removeNodeFromParent(draggedNode);

        // Dodaj na novu poziciju
        if (childIndex == -1 || childIndex > parent.getChildCount()) {
            childIndex = parent.getChildCount();
        }

        // Ako pomeramo unazad u istom parent-u, podesi index
        if (oldIndex < childIndex && oldParent == parent) {
            childIndex--;
        }

        ((GraffNodeComposite) parent.getGraffNode()).getChilds().add(childIndex, draggedNode.getGraffNode());
        model.insertNodeInto(draggedNode, parent, childIndex);

        // Selektuj novi node
        TreePath newPath = new TreePath(model.getPathToRoot(draggedNode));
        tree.setSelectionPath(newPath);
        tree.scrollPathToVisible(newPath);

        return true;
    }

    /**
     * Inner klasa za enkapsulaciju transferabilnog node-a
     */
    private class NodesTransferable implements Transferable {
        private GraffTreeItem node;

        public NodesTransferable(GraffTreeItem node) {
            this.node = node;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return node;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return nodesFlavor.equals(flavor);
        }
    }
}
