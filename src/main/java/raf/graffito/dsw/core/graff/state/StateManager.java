package raf.graffito.dsw.core.graff.state;

public class StateManager {

    private State current;

    public SelectState selectState;
    public MoveState moveState;
    public ResizeState resizeState;
    public RotateState rotateState;

    public StateManager() {

        selectState = new SelectState();
        moveState = new MoveState();
        resizeState = new ResizeState();
        rotateState = new RotateState();

        current = selectState;
    }


    public void setSelectState() { current = selectState; }
    public void setMoveState()   { current = moveState; }
    public void setResizeState() { current = resizeState; }
    public void setRotateState() { current = rotateState; }

    public State getCurrent() {return current;}
}
