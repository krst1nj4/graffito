package raf.graffito.dsw.core;

import lombok.Getter;
import raf.graffito.dsw.controller.*;
import raf.graffito.dsw.gui.swing.controllers.ChangeWindowModeAction;
import raf.graffito.dsw.gui.swing.controllers.RedoAction;
import raf.graffito.dsw.gui.swing.controllers.UndoAction;

import java.util.ArrayList;
import java.util.List;

@Getter

public class ActionManager {
    private ExitAction exitAct;
    private AboutUsAction aboutUsAct;
    private DeleteNodeAction deleteNodeAct;
    private EditAction editAction;
    private NewNodeAction newNodeAction;
    private OpenProjectAction openProjectAction;
    private SaveAsAction saveAsAction;
    private SaveAction saveAction;
    private LoadProjectAction loadProjectAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private ChangeWindowModeAction normalModeAction;
    private ChangeWindowModeAction smallModeAction;
    private ChangeWindowModeAction fullModeAction;

    private List<AbstractGraffAction> allActions;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        allActions = new ArrayList<>();

        exitAct = new ExitAction();
        aboutUsAct = new AboutUsAction();
        deleteNodeAct = new DeleteNodeAction();
        editAction = new EditAction();
        newNodeAction = new NewNodeAction();
        openProjectAction = new OpenProjectAction();
        saveAsAction = new SaveAsAction();
        saveAction = new SaveAction();
        loadProjectAction = new LoadProjectAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();

        normalModeAction = new ChangeWindowModeAction("Normal");
        smallModeAction = new ChangeWindowModeAction("Small");
        fullModeAction = new ChangeWindowModeAction("Fullscreen");

        allActions.add(exitAct);
        allActions.add(aboutUsAct);
        allActions.add(newNodeAction);
        allActions.add(deleteNodeAct);
        allActions.add(editAction);
        allActions.add(openProjectAction);
        allActions.add(saveAction);
        allActions.add(saveAsAction);
        allActions.add(loadProjectAction);
        allActions.add(undoAction);
        allActions.add(redoAction);
    }

}
