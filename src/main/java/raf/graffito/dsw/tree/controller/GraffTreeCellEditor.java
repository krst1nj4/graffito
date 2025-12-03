package raf.graffito.dsw.tree.controller;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class GraffTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField edit = null;

    public GraffTreeCellEditor(JTree tree, DefaultTreeCellRenderer render) {
        super(tree, render);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object editable, boolean isSelected, boolean isExpanded, boolean isList, int index) {
        clickedOn = editable;
        edit = new JTextField(editable.toString());
        edit.addActionListener(this);
        return edit;
    }
}
