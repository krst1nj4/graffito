package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.gui.swing.controller.ActionManager;
import raf.graffito.dsw.gui.swing.tree.GraffTree;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private ActionManager actionManager;
    private MyMenuBar menu;
    private JToolBar toolbar;
    private GraffTree graffTree;

    // Buduća polja za sve komponente view-a na glavnom prozoru

    private MainFrame() {
        initialize();
        actionManager = new ActionManager();
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        toolbar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolbar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora

        JTree workspace = graffTree.
    }

    public static MainFrame getInstance() {
        if(instance == null) {
            instance = new MainFrame();

        }
        return instance;
    }
}
