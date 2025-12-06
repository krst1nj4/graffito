package raf.graffito.dsw.tree.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.messages.MessageType;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class GraffTreeCellEditor extends DefaultTreeCellEditor {

    private JTextField tf;
    private GraffTreeItem editItem;

    public GraffTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer){
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value,
                                                boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {
        if(value instanceof GraffTreeItem){
            editItem = (GraffTreeItem)value;
            tf = new JTextField(editItem.getGraffNode().getName());
            tf.selectAll();


            tf.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    stopCellEditing();
                }
            });

            return tf;
        }

        return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
    }

    /// provera da li ime vec postoji i azuriranje imena cvora
    @Override
    public Object getCellEditorValue() {
        if(tf != null && editItem != null){
            String name = tf.getText().trim();

            if(!name.isEmpty()){
                if(editItem.getGraffNode().getParent() != null && editItem.getGraffNode().getParent().findByName(name) != null){
                    ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.GRESKA, "Ime vec postoji!");
                    return editItem;
                }

                ApplicationFramework.getInstance().getDialogMsgGenerator().generateMessage(MessageType.OBAVESTENJE, "Cvor: " + editItem.getGraffNode().getName() + " promenjeno u: " + name.trim());
                editItem.getGraffNode().setName(name);
            }
        }

        return super.getCellEditorValue();
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(event instanceof MouseEvent) {
            MouseEvent me = (MouseEvent)event;
            return me.getClickCount() >= 3;
        }
        return true;
    }
}
