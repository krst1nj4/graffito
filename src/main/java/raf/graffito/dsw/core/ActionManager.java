package raf.graffito.dsw.core;

import lombok.Getter;
import raf.graffito.dsw.controller.*;

@Getter

public class ActionManager {
    private ExitAction exitAct;
    private AboutUsAction aboutUsAct;
    private DeleteNodeAction deleteNodeAct;
    private EditAction editAction;
    private NewNodeAction newNodeAction;
    private OpenProjectAction openProjectAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAct = new ExitAction();
        aboutUsAct = new AboutUsAction();
        deleteNodeAct = new DeleteNodeAction();
        editAction = new EditAction();
        newNodeAction = new NewNodeAction();
        openProjectAction = new OpenProjectAction();
    }

}
