package raf.graffito.dsw.core.graff.state;

import lombok.Getter;

@Getter
public class StateManager {
    //Trenutno aktivan state
    private State current;

    public SelectState selectState;
    public MoveState moveState;
    public ResizeState resizeState;
    public RotateState rotateState;

    private AddState addState;

    public StateManager() {

        selectState = new SelectState(this);
        moveState = new MoveState(this, selectState);
        resizeState = new ResizeState(this, selectState);
        rotateState = new RotateState(this, selectState);

        current = selectState;
    }


    public void setSelectState() { current = selectState; }
    public void setMoveState()   { current = moveState; }
    public void setResizeState() { current = resizeState; }
    public void setRotateState() { current = rotateState; }

    public void setAddState(AddState addState) { this.addState = addState; current = addState; }

    public State getCurrent() {return current;}
}
