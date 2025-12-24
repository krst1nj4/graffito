package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.factory.ImageElementFactory;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.core.graff.state.AddState;
import raf.graffito.dsw.core.graff.state.StateManager;
import raf.graffito.dsw.gui.swing.views.SlideView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGalleryPanel extends JPanel {

    private StateManager stateManager;
    private JPanel tumbnailPanel;
    private SlideView slideView;

    public ImageGalleryPanel(StateManager stateManager) {
        this.stateManager = stateManager;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 0, 0 ,0));

        JButton btnLoadDisk = new JButton("Ucitaj sliku sa diska");
        btnLoadDisk.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoadDisk.addActionListener(e -> openFIleChooser());

        add(btnLoadDisk, BorderLayout.NORTH);

        tumbnailPanel = new JPanel();
        tumbnailPanel.setLayout(new BoxLayout(tumbnailPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(tumbnailPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(140, 300));

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setCurrentView(SlideView slideView){
        this.slideView = slideView;
    }

    private void openFIleChooser(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FileNameExtensionFilter("images", "jpg", "jpeg", "png"));

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            for(File file : fileChooser.getSelectedFiles()){
                addTumbnail(file);
            }
        }
    }

    private void addTumbnail(File file) {
        try{
            BufferedImage fullImage = ImageIO.read(file);
            if(fullImage == null) return;

            Image scaledImg = fullImage.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImg);

            JButton thumbButton = new JButton(icon);
            thumbButton.setToolTipText(file.getName());
            thumbButton.setBackground(Color.WHITE);
            thumbButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            thumbButton.addActionListener(e -> {
                if(slideView == null){
                    JOptionPane.showMessageDialog(this, "Nije selektovan slajd!");
                    return;
                }
                stateManager.setAddState();

                AddState addState = stateManager.getAddState();
                addState.setFactory(new ImageElementFactory(file.getAbsolutePath()));

                addState.misKliknut(-1, -1, slideView);

            });

            tumbnailPanel.add(thumbButton);
            tumbnailPanel.add(Box.createVerticalStrut(5));

            tumbnailPanel.revalidate();
            tumbnailPanel.repaint();


        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
