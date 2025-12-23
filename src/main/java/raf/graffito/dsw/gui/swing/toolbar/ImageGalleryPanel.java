package raf.graffito.dsw.gui.swing.toolbar;

import raf.graffito.dsw.core.graff.factory.ImageElementFactory;
import raf.graffito.dsw.core.graff.state.AddState;
import raf.graffito.dsw.core.graff.state.StateManager;

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
                stateManager.setAddState();
                if(stateManager.getCurrent() instanceof AddState){
                    ((AddState) stateManager.getCurrent()).setFactory(new ImageElementFactory(fullImage));
                }
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
