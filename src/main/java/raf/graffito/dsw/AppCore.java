package raf.graffito.dsw;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.core.Gui;
import raf.graffito.dsw.gui.swing.SwingGui;
import raf.graffito.dsw.repozitorijum.GraffRepositoryImplements;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        GraffRepository graffRepository = new GraffRepositoryImplements();
        appCore.initialize(gui, graffRepository);
        appCore.run();
    }
}