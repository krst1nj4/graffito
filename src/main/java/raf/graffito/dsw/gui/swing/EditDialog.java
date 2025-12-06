package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import raf.graffito.dsw.core.graff.model.Project;

import javax.swing.*;
import java.awt.*;


public class EditDialog {
    private JTextField name;
    private JTextField author;
    private JPanel panel;

    public EditDialog(Project project) {
        initComponents(project);
    }

    private void initComponents(Project project) {
        panel = new JPanel(new GridLayout(2, 2, 5, 5));
        name = new JTextField(project.getName());
        author = new JTextField(project.getAutor());

        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Author:"));
        panel.add(author);
    }

    public boolean showDialog(Component parent) {
        int res = JOptionPane.showConfirmDialog(parent, panel,
                "Izmenite info o projektu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        return res == JOptionPane.OK_OPTION;
    }

    public String getProjectName() {
        return name.getText().trim();
    }

    public String getAuthor() {
        return author.getText().trim();
    }


}
