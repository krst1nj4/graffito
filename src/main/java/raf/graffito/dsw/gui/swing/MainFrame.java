package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.core.messages.Poruka;
import raf.graffito.dsw.observer.Subscriber;
import raf.graffito.dsw.tree.model.GraffTree;
import raf.graffito.dsw.tree.model.GraffTreeImplements;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame implements Subscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private MyMenuBar menu;
    private JToolBar toolbar;
    private GraffTree graffTree;

    // Buduća polja za sve komponente view-a na glavnom prozoru

    private MainFrame() {

    }

    private void initialize(){
        actionManager = new ActionManager();
        graffTree = new GraffTreeImplements();
        initializeGUI();
    }

    public void initTree() {
        graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
    }

    private void initializeGUI() {
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

        JTree workspace = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        JPanel panel = new JPanel();

        JScrollPane scroll = new JScrollPane(workspace);
        scroll.setMinimumSize(new Dimension(200, 150));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, panel);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance() {
        if(instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void update(Object notif) {
        Poruka msg = (Poruka) notif;
        switch (msg.getType()) {
            case GRESKA:
                JOptionPane.showMessageDialog(this, msg.getContent(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                break;
            case OBAVESTENJE:
                JOptionPane.showMessageDialog(this, msg.getContent(), "OBAVESTENJE", JOptionPane.WARNING_MESSAGE);
                break;
            default:
                break;

        }
    }
}
