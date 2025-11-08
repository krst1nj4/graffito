package raf.graffito.dsw.gui.swing;



import raf.graffito.dsw.controller.AboutUsAction;
import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction exitAction = new ExitAction();
        fileMenu.add(exitAction);
        AboutUsAction aboutUsAction = new AboutUsAction();
        fileMenu.add(aboutUsAction);
        add(fileMenu);
    }
}
