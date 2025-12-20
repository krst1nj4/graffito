package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.ActionManager;
import raf.graffito.dsw.core.messages.Poruka;
import raf.graffito.dsw.gui.swing.views.ProjectView;
import raf.graffito.dsw.observer.Subscriber;
import raf.graffito.dsw.tree.model.GraffTree;
import raf.graffito.dsw.tree.model.GraffTreeImplements;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

/**
 * MainFrame -> view za glavni prozor
 */

public class MainFrame extends JFrame implements Subscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private MyMenuBar menu;
    private JToolBar toolbar;
    private GraffTree graffTree;
    private ProjectView projectView;
    private JSplitPane splitPane;


    private MainFrame() {

    }

    private void initialize(){
        graffTree = new GraffTreeImplements();
        actionManager = new ActionManager();
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

        graffTree = new GraffTreeImplements();

//        JTree workspaceTree = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());

//        System.out.println(ApplicationFramework.getInstance().getGraffRepository().getWorkspace().getChildren());

        JScrollPane treeScrollPane = new JScrollPane((JComponent) graffTree);
        treeScrollPane.setPreferredSize(new Dimension(250, 0));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(createPlaceholderPanel());
        splitPane.setDividerLocation(250);

        add(splitPane, BorderLayout.CENTER);
    }

    public static MainFrame getInstance() {
        if(instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    private JPanel createPlaceholderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Workspace", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setForeground(Color.GRAY);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
        splitPane.setRightComponent(projectView);
    }

    @Override
    public void update(Object notif) {
        Poruka msg = (Poruka) notif;
        switch (msg.getType()) {
            case GRESKA:
                JOptionPane.showMessageDialog(this, msg.getContent(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                break;
            case OBAVESTENJE:
                JOptionPane.showMessageDialog(this, msg.getContent(), "OBAVESTENJE", JOptionPane.PLAIN_MESSAGE);
                break;
            case UPOZORENJE:
                JOptionPane.showMessageDialog(this, msg.getContent(), "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            default:
                break;

        }
    }
}
