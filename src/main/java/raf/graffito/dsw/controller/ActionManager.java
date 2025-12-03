package raf.graffito.dsw.controller;

import lombok.Getter;

@Getter

public class ActionManager {
    private ExitAction exitAct;
    private AboutUsAction aboutUsAct;
    private NewProjectAction newProjectAct;
    private DeleteNodeAction deleteNodeAct;
    private EditNameAction editNameAct;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAct = new ExitAction();
        aboutUsAct = new AboutUsAction();
        newProjectAct = new NewProjectAction();
        deleteNodeAct = new DeleteNodeAction();
        editNameAct = new EditNameAction();
    }

}
