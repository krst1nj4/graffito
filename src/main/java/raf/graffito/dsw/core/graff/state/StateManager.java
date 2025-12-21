package raf.graffito.dsw.core.graff.state;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.views.SlideView;

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

    private SlideView  slideView;

    public StateManager(SlideView slideView) {

        selectState = new SelectState(this);
        moveState = new MoveState(this, selectState);
        resizeState = new ResizeState(this, selectState);
        rotateState = new RotateState(this, selectState);

        zoomState = new ZoomState(slideView);
        current = selectState;
    }


    public void setSelectState() { current = selectState; }
    public void setMoveState()   { current = moveState; }
    public void setResizeState() { current = resizeState; }
    public void setRotateState() { current = rotateState; }
    public void setZoomState() { current = zoomState; }

    public void setAddState(AddState addState) { this.addState = addState; current = addState; }

}
