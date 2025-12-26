package raf.graffito.dsw.core.graff.state;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StateManager {
    //Trenutno aktivan state
    private State current;

    public SelectState selectState;
    public MoveState moveState;
    public ResizeState resizeState;
    public RotateState rotateState;
    public ZoomState zoomState;
    private AddState addState;
    private DeleteState deleteState;

    private CopyState copyState;
    private PasteState pasteState;
    private List<SlideElement> clipBoard = new ArrayList<>();

    public StateManager() {
        init();
    }

    private void init(){
        selectState = new SelectState();
        moveState = new MoveState(this.selectState);
        resizeState = new ResizeState(this.selectState);
        rotateState = new RotateState(this.selectState);
        addState = new AddState();
        deleteState = new DeleteState(this.selectState);

        copyState = new CopyState(this);
        pasteState = new PasteState(this);

        zoomState = new ZoomState();
        current = selectState;

    }


    public void setSelectState() { current = selectState; }
    public void setMoveState()   { current = moveState; }
    public void setResizeState() { current = resizeState; }
    public void setRotateState() { current = rotateState; }
    public void setZoomState() { current = zoomState; }
    public void setDeleteState() { current = deleteState; }
    public void setAddState() { current = addState; }
    public void setCopyState() { current = copyState; }
    public void setPasteState() { current = pasteState; }

}
