package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.ActionManager;
import raf.graffito.dsw.core.messages.Poruka;
import raf.graffito.dsw.gui.swing.toolbar.DesniToolbar;
import raf.graffito.dsw.gui.swing.views.ProjectView;
import raf.graffito.dsw.gui.swing.views.SlideView;
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
    private Dimension normalSize;
    private Dimension smallSize;


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
        int screenHeight = (int) (screenSize.getHeight() * 0.8);
        int screenWidth = (int) (screenSize.getWidth() * 0.8);
        normalSize = new Dimension(screenWidth, screenHeight);

        int smallHeight = (int) (normalSize.getHeight() * 0.5);
        int smallWidth = (int) (normalSize.getWidth() * 0.5);

        smallSize = new Dimension(smallWidth, smallHeight);

        setSize(normalSize);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        toolbar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolbar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora

        graffTree = new GraffTreeImplements();

        JScrollPane treeScrollPane = new JScrollPane((JComponent) graffTree);
        treeScrollPane.setPreferredSize(new Dimension(150, 0));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(createPlaceholderPanel());
        splitPane.setResizeWeight(0.15);
        splitPane.setDividerLocation(175);

        add(splitPane, BorderLayout.CENTER);

        updateUIScaling(1.0);
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

    public void setActiveSlideView(SlideView slideView) {

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

    public void setModeNormal(){
        setExtendedState(JFrame.NORMAL);
        setSize(normalSize);
        setLocationRelativeTo(null);

        if(splitPane != null) splitPane.setDividerLocation(250);

        updateUIScaling(1.0);
    }

    public void setModeSmall(){
        setExtendedState(JFrame.NORMAL);
        setSize(smallSize);
        setLocationRelativeTo(null);

        if(splitPane != null) splitPane.setDividerLocation(130);
        updateUIScaling(0.8);
    }

    public void setModeFullScreen(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        if(splitPane != null) splitPane.setDividerLocation(300);

        updateUIScaling(1.4);
    }

    private void updateUIScaling(double scaleFactor) {

        int baseFontSize = 12;
        int baseIconSize = 24;
        int baseTreeRowHeight = 20;

        int newFontSize = (int) (baseFontSize * scaleFactor);
        int newIconSize = (int) (baseIconSize * scaleFactor);
        int newRowHeight = (int) (baseTreeRowHeight * scaleFactor);

        if (newFontSize < 10) newFontSize = 10;

        Font newFont = new Font("Segoe UI", Font.PLAIN, newFontSize);
        updateComponentFont(this, newFont);

        for (AbstractGraffAction action : actionManager.getAllActions()) {
            action.setIconSize(newIconSize);
        }

        if (graffTree instanceof JTree) {
            JTree tree = (JTree) graffTree;
            tree.setRowHeight(newRowHeight);
            tree.setFont(newFont);
            SwingUtilities.updateComponentTreeUI(tree);
        }

        if (splitPane != null) {
            int newDividerLoc = (int) (175 * scaleFactor);
            splitPane.setDividerLocation(newDividerLoc);
        }

        this.revalidate();
        this.repaint();
    }

    private void updateComponentFont(Component comp, Font font) {
        comp.setFont(font);
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                updateComponentFont(child, font);
            }
        }
        if (comp instanceof JFrame) {
            JMenuBar bar = ((JFrame) comp).getJMenuBar();
            if (bar != null) {
                updateComponentFont(bar, font);
            }
        }
    }

    private void scaleAllIcons(int size){


    }
}
