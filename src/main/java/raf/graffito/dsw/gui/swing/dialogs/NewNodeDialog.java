package raf.graffito.dsw.gui.swing.dialogs;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class NewNodeDialog {


    public enum Tip {
        PRESENTATION("Prezentacija"),
        SLIDE("Slide");

        private String ime;

        Tip(String ime) {
            this.ime = ime;
        }

        @Override
        public String toString() {
            return ime;
        }
    }

    private JComboBox<Tip> typeCB;
    @Getter
    private Tip selectedTip;

    public NewNodeDialog() {
        typeCB = new JComboBox<Tip>(Tip.values());
    }

    public boolean showView(Component parent) {
        int rez = JOptionPane.showConfirmDialog(
                parent,
                typeCB,
                "Izaberite tip",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if(rez == JOptionPane.OK_OPTION) {
            selectedTip = (Tip) typeCB.getSelectedItem();
            return true;
        }

        return false;
    }

}
