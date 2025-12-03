package raf.graffito.dsw.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;

import javax.swing.tree.DefaultMutableTreeNode;


@Getter
@Setter

/// Ova klasa predstavlja jedan cvor u stablu
/// DefaultMutableTreeNode -> moze da se menjaju, dodaju i uklanjaju cvorovi u/iz stabla
public class GraffTreeItem extends DefaultMutableTreeNode {
    private GraffNode graffNode;

    public GraffTreeItem(GraffNode nodeModel) {
        this.graffNode = nodeModel;
    }

    @Override
    public String toString() {
        return graffNode.getName();
    }

    public void setName(String name) {
        this.graffNode.setName(name);
    }
}
