package raf.graffito.dsw.tree.model;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.graff.model.Presentation;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.NewNodeDialog;
import raf.graffito.dsw.tree.controller.GraffTreeCellEditor;
import raf.graffito.dsw.tree.view.GraffTreeCellRenderer;
import raf.graffito.dsw.tree.view.GraffTreeView;
import raf.graffito.dsw.core.graff.model.Project;
import raf.graffito.dsw.core.graff.model.Workspace;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class GraffTreeImplements extends JTree implements GraffTree {

    private GraffTreeView graffTreeView;
    private DefaultTreeModel treeModel;
    private GraffTreeCellRenderer cellRenderer;
    private GraffTreeCellEditor cellEditor;

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
    public void addChild(GraffTreeItem parent, GraffNode child) {
        if(parent instanceof GraffTreeItem && child != null) {
            GraffTreeItem graffParent = (GraffTreeItem) parent;

            if(graffParent.getGraffNode() instanceof GraffNodeComposite) {
                if(graffParent.getGraffNode().findByName(child.getName()) != null) {
                    ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "Ime vec postoji!");
                    return;
                }
                ((GraffNodeComposite) graffParent.getGraffNode()).addChild(child);
            }

            GraffTreeItem novi = new GraffTreeItem(child);
            treeModel.insertNodeInto(novi, graffParent, graffParent.getChildCount());

            if(child instanceof GraffNodeComposite) {
                GraffNodeComposite com =  (GraffNodeComposite) child;
                for(GraffNode grandchild : com.getChilds()) {
                    addChild(novi, grandchild);
                }
            }

            TreePath path = new TreePath(treeModel.getPathToRoot(novi));
        }
    }

    @Override
    public GraffTreeItem getSelectedNode() {
        return (GraffTreeItem) graffTreeView.getLastSelectedPathComponent();
    }

    @Override
    public boolean createChild(DefaultMutableTreeNode node) {
        if(node == null) {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "Morate izabrati cvor!");
            return false;
        }

        if(!(node instanceof GraffTreeItem)) {
            return false;
        }

        GraffTreeItem graffParent = (GraffTreeItem) node;
        GraffNode parentNode = graffParent.getGraffNode();

        GraffNode novi = null;

        if(parentNode instanceof Workspace) {
            novi = ApplicationFramework.getInstance().getGraffRepository().getWorkspace();
        } else if(parentNode instanceof Project) {
            NewNodeDialog pv = new NewNodeDialog();

            if(pv.showView(MainFrame.getInstance())) {
                NewNodeDialog.Tip tip = pv.getSelectedTip();

                if(tip == NewNodeDialog.Tip.PRESENTATION) {
                    novi = ApplicationFramework.getInstance().getGraffRepository().createFactory("presentation").createGraffNode((GraffNodeComposite) parentNode);
                } else {
                    novi = ApplicationFramework.getInstance().getGraffRepository().createFactory("slide").createGraffNode((GraffNodeComposite) parentNode);
                }
            }
        } else if(parentNode instanceof Presentation) {
            novi = ApplicationFramework.getInstance().getGraffRepository().createFactory("presentation").createGraffNode((GraffNodeComposite) parentNode);
        } else {
            ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.UPOZORENJE, "Ne mozete dodati novi cvor izabranom cvoru!");
            return false;
        }

        if(novi != null) {
            addChild(graffParent, novi);
        }

        return false;
    }

    @Override
    public boolean deleteChild(DefaultMutableTreeNode node) {
        return false;
    }

    @Override
    public void refreshTree() {
        treeModel.reload();
    }
}
