package raf.graffito.dsw.gui.swing;



import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAct());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAct());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAct());

        this.add(fileMenu);
    }
}
