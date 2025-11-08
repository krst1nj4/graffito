package raf.graffito.dsw.gui.swing.controller;

import lombok.Getter;

@Getter

public class ActionManager {
    private ExitAction exitAct;
    private AboutUsAction aboutUsAct;
    private NewProjectAction newProjectAct;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAct = new ExitAction();
        aboutUsAct = new AboutUsAction();
        newProjectAct = new NewProjectAction();
    }

}
